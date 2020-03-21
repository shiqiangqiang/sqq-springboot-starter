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
import org.springframework.web.servlet.ModelAndView;
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
        // 校验是否是重复请求(也适合模拟校验登录)
        boolean isRepeatSubmit = isRepeatSubmit(request);
        if (isRepeatSubmit){
            // 校验没有通过，重定向到staticpage.html页面
//            response.sendRedirect("thymeleaf/file01/staticpage");
            request.getRequestDispatcher("/thymeleaf/toStaticPage").forward(request, response);
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("RequestTokenInterceptor postHandle, request:{}", request);
    }

    /**
     * 在整个请求结束后调用，也就是在DispatcherServlet渲染了对应视图之后执行
     * （主要用于进行资源清理等工作，比如缓存、垃圾回收等）
     */
    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        log.info("RequestTokenInterceptor afterCompletion, request:{}", request);
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
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
        if (StringUtils.isEmpty(clientToken)){
            return true;
        }
        // 获取request中的请求参数
        Map<String, String> requestParamMap = RequestHandleUtil.getReqParam(request);
        if (requestParamMap == null || StringUtils.isEmpty(requestParamMap.get(reqParamUserId))){
            return true;
        }
        // 用户ID
        String userId = requestParamMap.get(reqParamUserId);
        // 如果redis中没有不存在token（令牌），则是重复提交
        String serverToken = redisOperator.get(RequestConstaint.REQUEST_TOKEN_REDIS_KEY_PREFIX, userId);
        if (StringUtils.isEmpty(serverToken)){
            return true;
        }
        // 存储在Session中的Token(令牌)与表单提交的Token(令牌)不同，则用户是重复提交了表单
        if (!clientToken.equals(serverToken)){
            return true;
        }
        // 校验通过后，删除原有的token
        redisOperator.deleteWithPrefix(RequestConstaint.REQUEST_TOKEN_REDIS_KEY_PREFIX, userId);
        return false;
    }

}