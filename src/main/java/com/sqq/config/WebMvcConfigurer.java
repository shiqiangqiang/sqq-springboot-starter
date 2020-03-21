package com.sqq.config;

import com.sqq.controller.interceptor.RequestTokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.sqq.controller.interceptor.OneInterceptor;
import com.sqq.controller.interceptor.TwoInterceptor;

/**
 * WEB 配置类
 */
@SuppressWarnings("deprecation")
@Configuration	// 该注解表明当前类是一个适配器
public class WebMvcConfigurer extends WebMvcConfigurerAdapter{
	/**
	 * 接口请求拦截器
	 */
	@Autowired
	private RequestTokenInterceptor requestTokenInterceptor;


	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		/**
		 * 拦截器按照顺序执行
		 */
		// 先被one拦截，后被two拦截，如果one拦截后返回false
		// 进行token拦截校验
		registry.addInterceptor(requestTokenInterceptor).addPathPatterns("/one/**");
		// 同时增加one和two两个拦截器
		// registry.addInterceptor(new TwoInterceptor()).addPathPatterns("/two/**").addPathPatterns("/one/**");
		super.addInterceptors(registry);

	}
	
}
