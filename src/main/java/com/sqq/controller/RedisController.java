package com.sqq.controller;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.sqq.domain.User;
import com.sqq.redis.RedisOperator;
import com.sqq.redis.StringRedisOperator;
import com.sqq.util.BackJsonResult;

/**
 * spring boot 整合redis 
 * @author shiqiangqiang
 *
 */
@RestController
@RequestMapping("redis")
public class RedisController {
	// redis的key前缀
	private static final String PREFIX_OF_KEY = "test";
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	@Autowired
	private StringRedisOperator stringRedisOperator;
	
	@Autowired
	private RedisOperator redisOperator;
	
	@GetMapping("/testStr")
	public BackJsonResult testStr(){
		/**
		 * 直接存放String
		 */
//		stringRedisTemplate.opsForValue().set("baidu", "http://www.baidu.com");
//		return BackJsonResult.ok(stringRedisTemplate.opsForValue().get("baidu"));
		/**
		 * 将对象转Json字符串后存放
		 */
		User user = new User("name123", "password123", 32, new Date(), "2222");
		String userJsonStr = JSON.toJSONString(user);
		stringRedisTemplate.opsForValue().set("userJsonStr", userJsonStr);
		User newUser = new User();
		newUser = JSON.parseObject(stringRedisTemplate.opsForValue().get("userJsonStr"), User.class);
		return BackJsonResult.ok("success", newUser);
	} 
	
	@GetMapping("/testStr2")
	public BackJsonResult testStr2(){
		/**
		 * 将对象转Json字符串后存放
		 */
		User user = new User("name456", "password456", 48, new Date(), "456");
		String userJsonStr = JSON.toJSONString(user);
		stringRedisOperator.set(PREFIX_OF_KEY, "userJsonStr2", userJsonStr);
		User newUser = JSON.parseObject(stringRedisOperator.get(PREFIX_OF_KEY, "userJsonStr2"), User.class);
		return BackJsonResult.ok("success", newUser);
	} 
	
	@GetMapping("/testSetObject")
	public BackJsonResult testSetObject(){
		User user = new User("object-name888", "object-password888", 49, new Date(), "object-888");
//		redisOperator.set(PREFIX_OF_KEY, "object-user", user);
		// 一分钟之后失效
		redisOperator.setWithExpireTime(PREFIX_OF_KEY, "object-user888", user, 1L, TimeUnit.MINUTES);
		return BackJsonResult.ok("success", null);
	} 
	
	@GetMapping("/testGetObject")
	public BackJsonResult testGetObject(){
		User newUser = (User) redisOperator.get(PREFIX_OF_KEY, "object-user888");
		return BackJsonResult.ok("success", newUser);
	} 
	
	
	
	
}
