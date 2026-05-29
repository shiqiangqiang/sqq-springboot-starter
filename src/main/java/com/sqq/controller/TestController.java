package com.sqq.controller;

import com.sqq.domain.City;
import com.sqq.util.BackJsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

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
	
//	@Autowired
//	private TestMapper testMapper;

	@PostMapping("/test-add-city")
	public BackJsonResult testAddCity(@RequestBody City city) {
		return BackJsonResult.ok("接口命中成功！city: " + city);
	}
	
//	@GetMapping("/insertTestObject")
//	public BackJsonResult insertTestObject(){
//		log.info("测试开始，开始时间：{}", getCurrentTime());
//		Test test = new Test();
//		try {
////			Date date = new Date();
////			test.setCreateTime(date);
////			test.setUpdateTime(date);
////			testMapper.insert(test);
//			test = testMapper.selectByPrimaryKey(2);
//		} catch (Exception e) {
//			log.error(MSG_TEST_EXCEPTION + ",发生异常时间：{},exception:{}", getCurrentTime(), e.getMessage());
//			return BackJsonResult.fail(MSG_TEST_EXCEPTION);
//		}
//		log.info("测试结束，结束时间：{}", getCurrentTime());
//		return BackJsonResult.ok(MSG_TEST_SUCCESS, test);
//	}
//
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
