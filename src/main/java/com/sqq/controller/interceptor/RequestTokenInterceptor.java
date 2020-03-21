package com.sqq.controller.interceptor;

import com.sqq.constaint.RequestConstaint;
import com.sqq.redis.StringRedisOperator;
import com.sqq.util.RequestHandleUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


/**
 * @description: 对请求进行token拦截
 * @author: shiqiangqiang
 * @createDate: 2020/3/21
 * @version: 1.0
 */
@Component
public class RequestTokenInterceptor implements HandlerInterceptor{
    private static final Logger log = LoggerFactory.getLogger(RequestTokenInterceptor.class);

    @Autowired
    private StringRedisOperator redisOperator;

    @Value("${req.header.token.name}")
    private String reqHeaderTokenName;

    /*
     * 请求中用户id字段名称
     */
    @Value("${req.param.userId}")
    private String reqParamUserId;

    /**
     * 在请求之前校验是否请求中的token（Controller方法调用之前）
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("RequestTokenInterceptor preHandle, request:{}", request);
        return !isRepeatSubmit(request);
    }

    /**
     * 校验是否是重复提交
     * 判断客户端提交上来的令牌和服务器端生成的令牌是否一致
     * @return  true 用户重复提交了表单
     *          false 用户没有重复提交表单
     */
    private boolean isRepeatSubmit(HttpServletRequest request){
        // 如果请求头中没有带token，用户是重复提交
        String clientToken = request.getHeader(reqHeaderTokenName);
        // 获取request中的请求参数
        Map<String, String> requestParamMap = RequestHandleUtil.getReqParam(request);
        if (requestParamMap == null || StringUtils.isEmpty(requestParamMap.get(reqParamUserId))){
            return true;
        }
        // 用户ID
        String userId = requestParamMap.get(reqParamUserId);
        if (StringUtils.isEmpty(clientToken)){
            return true;
        }
        // 如果redis中没有不存在token（令牌），则是重复提交
        String serverToken = redisOperator.get(RequestConstaint.REQUEST_TOKEN_REDIS_KEY_PREFIX, userId);
        if (StringUtils.isEmpty(serverToken)){
            return true;
        }
        // 存储在Session中的Token(令牌)与表单提交的Token(令牌)不同，则用户是重复提交了表单
        if (!clientToken.equals(serverToken)){
            return true;
        }

        return false;
    }

}