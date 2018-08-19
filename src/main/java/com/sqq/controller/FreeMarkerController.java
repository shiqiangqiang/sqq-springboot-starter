package com.sqq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sqq.domain.Resource;

/**
 * freemarker模板测试入口
 * @author shiqiangqiang
 *
 */
@RequestMapping("freemarker")
@Controller
public class FreeMarkerController {
	@Autowired
	private Resource resource;
	
	/**
	 * Description: 跳转至静态页面
	 * @return
	 * @author shiqiangqiang  
	 * @date 2018年8月18日
	 */
	@GetMapping("/toStaticPage")
	public String toStaticPage(){
		return "freemarker/file1/staticpage";
	}
	
	/**
	 * Description: 跳转至动态显示页面
	 * @return
	 * @author shiqiangqiang  
	 * @date 2018年8月18日
	 */
	@GetMapping("/toDynamicPage")
	public String toDynamicPage(ModelMap map){
		map.addAttribute("resource", resource);
		return "freemarker/file1/dynamicpage";
	}
	
	
}
