package com.sqq.controller;

import com.sqq.util.BackJsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
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
    // 模拟产品001
    private static final String LOCK_KEY_PRODUCT001 = "product_001";

    private static final String STOCK_PRODUCT_001 = "stock_product_001";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 尝试手工编写分布式锁，模拟高并发售卖产品
     * @return
     */
    @PostMapping("mockHighConcurrentcySale")
    public BackJsonResult mockHighConcurrentcySale(){
        try {
            String clientId = UUID.randomUUID().toString();
            // 尝试加锁，setNx，并设置过期时间为10秒,使用原子操作命令
            Boolean result = stringRedisTemplate.opsForValue().setIfAbsent(LOCK_KEY_PRODUCT001, clientId);
            stringRedisTemplate.expire(LOCK_KEY_PRODUCT001, 10, TimeUnit.SECONDS);

            // 如果加锁成功则获取到该锁，库存减少5个
            if (!result) {
                return BackJsonResult.fail("failed product_001");
            }
            int stockNum = Integer.parseInt(stringRedisTemplate.opsForValue().get(STOCK_PRODUCT_001));
            if (stockNum > 0) {
                int realStock = stockNum - 1;
                stringRedisTemplate.opsForValue().set(STOCK_PRODUCT_001, realStock + "");  // jedis.set(key,value)
                System.out.println("扣减成功，剩余库存：" + realStock);
            } else {
                System.out.println("扣减失败，库存不足！");
            }

        }finally {
            // 操作完毕，需要finally删除Redis锁
            stringRedisTemplate.delete(LOCK_KEY_PRODUCT001);
        }

        // 操作完毕，需要finally删除Redis锁
        return BackJsonResult.ok("success!");
    }


}
