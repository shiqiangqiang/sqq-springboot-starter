package com.sqq.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sqq.domain.Resource;
import com.sqq.util.BackJsonResult;

/**
 * 资源文件映射到类对象测试
 * @author shiqiangqiang
 *
 */
@RestController
@RequestMapping("/resource")
public class ResourceController {
	
	@Autowired
	private Resource resource; 	 // 使用自动注解方式，将resource.properties文件中内容读取到对象resource中
	
	@GetMapping("/getResourceFileValues")
	public BackJsonResult getResourceFileValues(){
		Resource bean = new Resource();
		BeanUtils.copyProperties(resource, bean);	// 将resource里面的值赋给bean对象，不能直接返回resource！！！
		return new BackJsonResult(bean);
	}
	
}
