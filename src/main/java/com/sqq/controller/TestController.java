package com.sqq.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sqq.domain.Test;
import com.sqq.mapper.TestMapper;
import com.sqq.util.BackJsonResult;

/**
 * 测试Controller
 * @author shiqiangqiang
 *
 */
@RestController
@RequestMapping("test")
public class TestController {
	private static Logger log = LoggerFactory.getLogger(TestController.class);
	
	private static final String MSG_TEST_EXCEPTION = "出现异常，测试失败！";
	private static final String MSG_TEST_SUCCESS = "测试成功！";
	
	private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Autowired
	private TestMapper testMapper;
	
	@GetMapping("/insertTestObject")
	public BackJsonResult insertTestObject(){
		log.info("测试开始，开始时间：{}", getCurrentTime());
		Test test = new Test();
		try {
//			Date date = new Date();
//			test.setCreateTime(date);
//			test.setUpdateTime(date);
//			testMapper.insert(test);
			test = testMapper.selectByPrimaryKey(2);
		} catch (Exception e) {
			log.error(MSG_TEST_EXCEPTION + ",发生异常时间：{},exception:{}", getCurrentTime(), e.getMessage());
			return BackJsonResult.fail(MSG_TEST_EXCEPTION);
		}
		log.info("测试结束，结束时间：{}", getCurrentTime());
		return BackJsonResult.ok(MSG_TEST_SUCCESS, test);
	}
	
	/**
	 * Description: 获取当前日期时间，格式yyyy-MM-dd hh24:mm:ss
	 * @return
	 * @author shiqiangqiang  
	 * @date 2018年8月26日
	 */
	private String getCurrentTime(){
		return simpleDateFormat.format(new Date());
	}
}
