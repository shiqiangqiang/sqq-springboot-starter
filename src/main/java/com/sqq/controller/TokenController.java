package com.sqq.controller;

import com.sqq.constaint.RequestConstaint;
import com.sqq.po.TokenParam;
import com.sqq.redis.StringRedisOperator;
import com.sqq.util.BackJsonResult;
import com.sqq.util.TokenProccessorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.StringUtils;

import java.util.concurrent.TimeUnit;

/**
 * @description: token处理接口
 * @author: shiqiangqiang
 * @createDate: 2020/3/21
 * @version: 1.0
 */
@RestController
@RequestMapping("/token")
public class TokenController {
    private static final Logger log = LoggerFactory.getLogger(TokenController.class);

    @Autowired
    private StringRedisOperator stringRedisOperator;

    /**
     * 获取一个新的token
     * @return
     */
    @RequestMapping(value = "getNewToken", method = RequestMethod.POST)
    public BackJsonResult getNewToken(@RequestBody TokenParam tokenParam){
        log.info("TokenController getNewToken, start... tokenParam:{}", tokenParam);
        if (tokenParam == null || StringUtils.isEmpty(tokenParam.getUserId())){
            return BackJsonResult.fail("缺少必要参数！");
        }
        // 生产token，并设置进入redis, 有效时间为5分钟
        String token = TokenProccessorUtil.getInstance().produceToken();
        stringRedisOperator.setWithExpireTime(RequestConstaint.REQUEST_TOKEN_REDIS_KEY_PREFIX, tokenParam.getUserId(), token, Long.parseLong("5"), TimeUnit.MINUTES);

        return BackJsonResult.ok(token);

    }

}