package com.sqq.controller;

import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sqq.asynctask.AsyncTestTask;
import com.sqq.util.BackJsonResult;

/**
 * 异步任务 Controller
 * @author shiqiangqiang
 *
 */
@RestController
@RequestMapping("async")
public class AsynchronizeController {
	
	private static final Logger log = LoggerFactory.getLogger(AsynchronizeController.class);
	
	@Autowired
	private AsyncTestTask asyncTestTask; 
	
	/**
	 * Description: 测试异步任务执行时间
	 * @return
	 * @author shiqiangqiang  
	 * @date 2018年8月26日
	 */
	@GetMapping("/testExecuteTime")
	public BackJsonResult testExecuteTime(){
		long startTime = System.currentTimeMillis();
		Future<Boolean> flag1 = asyncTestTask.task1();
		Future<Boolean> flag2 = asyncTestTask.task2();
		Future<Boolean> flag3 = asyncTestTask.task3();
		while (true){
			if (flag1.isDone() && flag2.isDone() && flag3.isDone()){
				break;
			}
		}
		long endTime = System.currentTimeMillis();
		String msg = "任务总执行时间：" + (endTime-startTime) + "毫秒";
		log.info(msg);
		return BackJsonResult.ok(msg);
	}
}
