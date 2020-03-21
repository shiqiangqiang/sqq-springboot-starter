package com.sqq.controller.interceptor;

import com.sqq.util.BackJsonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("one")
@RestController
public class OneController {
	
	@PostMapping("/interceptorTest")
	public BackJsonResult interceptorTest(){
		String msg = "已进入执行OneController的interceptorTest方法！";
		System.out.println(msg);
		return BackJsonResult.ok(msg);
	}

	@GetMapping("/interceptorTest002")
	public BackJsonResult interceptorTest002(){
		String msg = "已进入执行OneController的interceptorTest002方法！";
		System.out.println(msg);
		return BackJsonResult.ok(msg);
	}
}
