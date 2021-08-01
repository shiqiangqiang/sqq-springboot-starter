package com.sqq.controller;

import com.sqq.util.BackJsonResult;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 基于Redis实现分布式锁, redisson
 */
@RestController
@RequestMapping("distributedLockBaseRedisson")
public class DistributedLockBaseRedisson {
    private static final Logger log = LoggerFactory.getLogger(DistributedLockBaseRedisson.class);

    // 模拟产品001
    private static final String LOCK_KEY_PRODUCT002 = "product_002";

    private static final String STOCK_PRODUCT_002 = "stock_product_002";

    // 接口请求数
    private static final String COUNT0801_COME_IN = "count0802:comein";
    // 售卖次数
    private static final String COUNT0801_SALE_OUT = "count0802:saleout";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private Redisson redisson;

    /**
     * 尝试手工编写分布式锁，模拟高并发售卖产品, redisson实现
     * @return
     */
    @GetMapping ("mockHighConcurrentcySaleRedisson")
    public BackJsonResult mockHighConcurrentcySaleRedisson(){
        log.info("请求进入2。。。。。。");
        // Redis原子自增操作
        RedisAtomicLong comeInCounter = new RedisAtomicLong(COUNT0801_COME_IN, stringRedisTemplate.getConnectionFactory());
        Long count0801ComeIn = comeInCounter.getAndIncrement();  // 自增1
        log.info("------count0802ComeIn：{}，------ 请求次数：{}", count0801ComeIn, count0801ComeIn + 1);

        // 使用Redisson尝试获取锁
        RLock redissonLock = redisson.getLock(LOCK_KEY_PRODUCT002);
        try {
            // 加锁，实现锁续命功能(看门狗功能，默认30秒)
            redissonLock.lock();
            int stockNum = Integer.parseInt(stringRedisTemplate.opsForValue().get(STOCK_PRODUCT_002));
            if (stockNum >= 5) {
                int realStock = stockNum - 5;
                stringRedisTemplate.opsForValue().set(STOCK_PRODUCT_002, realStock + "");  // jedis.set(key,value)
                log.info("2扣减成功，剩余库存：" + realStock);
                // 使用redis统计售卖次数，
                RedisAtomicLong saleOutCounter = new RedisAtomicLong(COUNT0801_SALE_OUT, stringRedisTemplate.getConnectionFactory());
                Long count0801SaleOut = saleOutCounter.getAndIncrement();  // 自增1
                log.info("------count0802SaleOut：{}，------ 售卖次数：{}", count0801SaleOut, count0801SaleOut + 1);
            } else {
                log.info("2扣减失败，库存不足！");
            }

        }catch (Exception ex){
            log.error("2库存扣减发生异常，ex:{}", ex);
        }finally {
            // 完成功能释放锁
            redissonLock.unlock();
        }
        log.info("2请求结束返回。。。。。。");
        // 操作完毕，需要finally删除Redis锁
        return BackJsonResult.ok("success!");
    }

}
