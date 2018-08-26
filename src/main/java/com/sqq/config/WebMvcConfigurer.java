package com.sqq.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.sqq.controller.interceptor.OneInterceptor;
import com.sqq.controller.interceptor.TwoInterceptor;

@SuppressWarnings("deprecation")
@Configuration	// 该注解表明当前类是一个适配器
public class WebMvcConfigurer extends WebMvcConfigurerAdapter{

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		/**
		 * 拦截器按照顺序执行
		 */
		// 先被one拦截，后被two拦截，如果one拦截后返回false
		registry.addInterceptor(new OneInterceptor()).addPathPatterns("/one/**");
		// 同时增加one和two两个拦截器
		registry.addInterceptor(new TwoInterceptor()).addPathPatterns("/two/**").addPathPatterns("/one/**");
		super.addInterceptors(registry);
	}
	
}
