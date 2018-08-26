package com.sqq.controller.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
/**
 * 拦截器 Two
 * @author shiqiangqiang
 *
 */
public class TwoInterceptor implements HandlerInterceptor{
	
	/**
	 * 在整个请求结束后调用，也就是在DispatcherServlet渲染了对应视图之后执行
	 * （主要用于进行资源清理等工作，比如缓存、垃圾回收等）
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
	
	/**
	 * 请求处理之后进行调用，但是在视图渲染之前（Controller方法调用之后）
	 */
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}
	
	/**
	 * 在请求之前进行调用（Controller方法调用之前）
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		System.out.println("被Two拦截，不放行！");
		return false;
	}
	
}
