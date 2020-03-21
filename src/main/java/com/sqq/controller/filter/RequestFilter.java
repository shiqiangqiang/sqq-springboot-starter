package com.sqq.controller.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * @description: 过滤器,对所有请求起作用 (注意Filter是javax.servlet.Filter)
 * @author: shiqiangqiang
 * @createDate: 2020/3/22
 * @version: 1.0
 */
@Component
public class RequestFilter implements Filter {
    private static final Logger log = LoggerFactory.getLogger(RequestFilter.class);

    /*
     * 这个方法只是在容器启动的时候执行
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("LoginFilter init, has come in... filterConfig:{}", filterConfig);
    }

    /*
     * 每个请求 进来和返回 都会经过这
     * 有请求进行或者响应都会执行(如果不执行chain.doFilter(request, response)，就表示被过滤后，后面就不会执行，直接返回了)
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("LoginFilter doFilter, has come in... ");
        filterChain.doFilter(servletRequest, servletResponse);

    }

    /**
     * 容器销毁的时候调用
     */
    @Override
    public void destroy() {
        log.info("LoginFilter destroy, has come in... ");
    }
}