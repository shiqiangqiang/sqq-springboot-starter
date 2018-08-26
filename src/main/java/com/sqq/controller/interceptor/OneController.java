package com.sqq.controller.interceptor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sqq.util.BackJsonResult;

@RequestMapping("one")
@RestController
public class OneController {
	
	@GetMapping("/interceptorTest")
	public BackJsonResult interceptorTest(){
		String msg = "已进入执行OneController的interceptorTest方法！";
		System.out.println(msg);
		return BackJsonResult.ok(msg);
	}
}
