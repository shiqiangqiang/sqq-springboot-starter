package com.sqq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sqq.util.BackJsonResult;

@RequestMapping("/errorController")
@Controller
public class ErrorController {
	
	@GetMapping("/error")
	public String error(){
		int i = 1/0;
		return "global-error"; 
	}
	
	@GetMapping("/ajaxError")
	public String ajaxError(){
		return "ajaxerror"; 
	}
	
	@PostMapping("/getAjaxError")
	@ResponseBody
	public BackJsonResult getAjaxError(){
		int i = 1/0;
		return BackJsonResult.ok(); 
	}
	
}	
