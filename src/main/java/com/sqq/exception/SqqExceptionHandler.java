package com.sqq.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * 全局异常处理类
 * @author shiqiangqiang
 *
 */
//@ControllerAdvice
public class SqqExceptionHandler {
	private static final Logger log = LoggerFactory.getLogger(SqqExceptionHandler.class);
	
	public static final String SQQ_ERROR_VIEW = "global-error";
	
//	@ExceptionHandler(value = Exception.class)
	public Object errorHandler(HttpServletRequest request, HttpServletResponse response, Exception e) throws Exception{
		log.error(e.getMessage());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("exception", e);
		modelAndView.addObject("url", request.getRequestURI());
		modelAndView.setViewName(SQQ_ERROR_VIEW);
		return modelAndView;
	}
}
