package com.sqq.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sqq.domain.Resource;
import com.sqq.domain.User;

/**
 * thymeleaf模板测试入口
 * @author shiqiangqiang
 *
 */
@RequestMapping("thymeleaf")
@Controller
public class ThymeleafController {
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
		return "thymeleaf/file01/staticpage";
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
		return "thymeleaf/file01/dynamicpage";
	}
	
	@GetMapping("toTestPage")
	public String toTestPage(ModelMap map){
		User user = new User();
		user.setName("孙悟空");
		user.setAge(24);
		user.setBirthday(new Date());
		user.setDescribe("<font color='red'><b>这是一段红色测试文字</b></font>");
		map.addAttribute("user", user);
		return "thymeleaf/file01/th/test";
	}
	
	@PostMapping("/postForm")
	public String postForm(User user,ModelMap map){
		map.addAttribute("username", user.getName());
		map.addAttribute("userage", user.getAge());
		return "thymeleaf/file01/th/postform";
	} 
	
	/**
	 * Description: 检测默认选择框
	 * @param map
	 * @return
	 * @author shiqiangqiang  
	 * @date 2018年8月19日
	 */
	@GetMapping("/toSelectedPage")
	public String toSelectedPage(ModelMap map){
		User user = new User();
		user.setName("lisi");
		map.addAttribute("user", user);
		return "thymeleaf/file01/th/selected";
	}
	
	/**
	 * Description: 检测循环输出 each
	 * @param map
	 * @return
	 * @author shiqiangqiang  
	 * @date 2018年8月19日
	 */
	@GetMapping("/toCirclePage")
	public String toCirclePage(ModelMap map){
		List<User> userList = new ArrayList<User>();
		// lisi
		User user = new User();
		user.setName("lisi");
		user.setAge(35);
		user.setBirthday(new Date());
		user.setDescribe("李四显神通");
		// 孙悟空
		User user2 = new User();
		user2.setName("孙悟空");
		user2.setAge(24);
		user2.setBirthday(new Date());
		user2.setDescribe("<font color='red'><b>这是一段红色测试文字</b></font>");
		// 王二
		User user3 = new User();
		user3.setName("王二");
		user3.setAge(15);
		user3.setBirthday(new Date());
		user3.setDescribe("王二会武功");
		// 加入List
		userList.add(user);
		userList.add(user2);
		userList.add(user3);
		map.addAttribute("userList", userList);
		return "thymeleaf/file01/th/circle";
	}
	
	/**
	 * Description: 检测循环输出 each
	 * @param map
	 * @return
	 * @author shiqiangqiang  
	 * @date 2018年8月19日
	 */
	@GetMapping("/toSwitchPage")
	public String toSwitchPage(ModelMap map){
		User user = new User();
		user.setName("superadmin");
		user.setAge(35);
		user.setBirthday(new Date());
		user.setDescribe("李四显神通");
		map.addAttribute("user", user);
		return "thymeleaf/file01/th/switch";
	}
	
}
