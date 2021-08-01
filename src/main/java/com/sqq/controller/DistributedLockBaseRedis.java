package com.sqq.controller;

import com.sqq.util.BackJsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 基于Redis实现分布式锁
 */
@RestController
@RequestMapping("distributedLockBaseRedis")
public class DistributedLockBaseRedis {
    private static final Logger log = LoggerFactory.getLogger(DistributedLockBaseRedis.class);

    // 模拟产品001
    private static final String LOCK_KEY_PRODUCT001 = "product_001";

    private static final String STOCK_PRODUCT_001 = "stock_product_001";

    // 接口请求数
    private static final String COUNT0801_COME_IN = "count0801:comein";
    // 售卖次数
    private static final String COUNT0801_SALE_OUT = "count0801:saleout";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 尝试手工编写分布式锁，模拟高并发售卖产品
     * @return
     */
    @GetMapping ("mockHighConcurrentcySale")
    public BackJsonResult mockHighConcurrentcySale(){
        log.info("请求进入。。。。。。");
        // Redis原子自增操作
        RedisAtomicLong comeInCounter = new RedisAtomicLong(COUNT0801_COME_IN, stringRedisTemplate.getConnectionFactory());
        Long count0801ComeIn = comeInCounter.getAndIncrement();  // 自增1
        log.info("------count0801ComeIn：{}，------ 请求次数：{}", count0801ComeIn, count0801ComeIn + 1);

        String clientId = UUID.randomUUID().toString();
        try {
            // 尝试加锁，setNx，并设置过期时间为10秒,使用原子操作命令
            Boolean result = stringRedisTemplate.opsForValue().setIfAbsent(LOCK_KEY_PRODUCT001, clientId);
            stringRedisTemplate.expire(LOCK_KEY_PRODUCT001, 10, TimeUnit.SECONDS);

            // 如果加锁成功则获取到该锁，库存减少5个
            if (!result) {
//                // 如果必须保证仓库库存需要卖完的化
//                while(true){
//                    Boolean result2 = stringRedisTemplate.opsForValue().setIfAbsent(LOCK_KEY_PRODUCT001, clientId);
//                    stringRedisTemplate.expire(LOCK_KEY_PRODUCT001, 10, TimeUnit.SECONDS);
//                    if(result2){
//                        break;
//                    }
//                    Thread.sleep(800);
//                }
//                log.info("加锁等待成功。。。");
                 log.info("加锁失败，return。。。");
                 return BackJsonResult.fail("failed product_001");
            }
            int stockNum = Integer.parseInt(stringRedisTemplate.opsForValue().get(STOCK_PRODUCT_001));
            if (stockNum >= 5) {
                int realStock = stockNum - 5;
                stringRedisTemplate.opsForValue().set(STOCK_PRODUCT_001, realStock + "");  // jedis.set(key,value)
                log.info("扣减成功，剩余库存：" + realStock);
                // 使用redis统计售卖次数，
                RedisAtomicLong saleOutCounter = new RedisAtomicLong(COUNT0801_SALE_OUT, stringRedisTemplate.getConnectionFactory());
                Long count0801SaleOut = saleOutCounter.getAndIncrement();  // 自增1
                log.info("------count0801SaleOut：{}，------ 售卖次数：{}", count0801SaleOut, count0801SaleOut + 1);
            } else {
                log.info("扣减失败，库存不足！");
            }

        }catch (Exception ex){
            log.error("库存扣减发生异常，ex:{}", ex);
        }finally {
            // 操作完毕，需要finally删除Redis锁, A线程不能删除B线程设置的key
            if (clientId.equals(stringRedisTemplate.opsForValue().get(LOCK_KEY_PRODUCT001))){
                stringRedisTemplate.delete(LOCK_KEY_PRODUCT001);
            }
        }
        log.info("请求结束返回。。。。。。");
        // 操作完毕，需要finally删除Redis锁
        return BackJsonResult.ok("success!");
    }

}
