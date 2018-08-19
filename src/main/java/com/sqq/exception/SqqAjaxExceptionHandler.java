package com.sqq.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.sqq.util.BackJsonResult;
/**
 * Ajax异常处理类
 * @author shiqiangqiang
 *
 */
@RestControllerAdvice
public class SqqAjaxExceptionHandler {
	private static final Logger log = LoggerFactory.getLogger(SqqAjaxExceptionHandler.class);
	
	public static final String SQQ_ERROR_VIEW = "ajaxerror";
	
	@ExceptionHandler(value = Exception.class)
	public BackJsonResult ajaxErrorHandler(HttpServletRequest request, HttpServletResponse response, Exception e) throws Exception{
		log.error(e.getMessage());
		return BackJsonResult.errorException(e.getMessage());
	}
	
	
}
