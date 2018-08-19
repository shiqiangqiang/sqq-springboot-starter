package com.sqq.controller;

import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sqq.domain.User;
import com.sqq.util.BackJsonResult;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@GetMapping("/getUser")
	public User getUser(){
		String name = "张三";
		String password = "123123";
		int age = 20;
		Date birthday = new Date();
		User user = new User(name, password, age, birthday,null);
		return user;
	}
	
	@GetMapping("/getUserJson")
	public BackJsonResult getUserJson(){
		String name = "张三";
		String password = "123123";
		int age = 20;
		Date birthday = new Date();
		User user = new User(name, password, age, birthday,null);
		return BackJsonResult.ok(user);
	}
}
