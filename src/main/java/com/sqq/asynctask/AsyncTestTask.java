package com.sqq.asynctask;

import java.util.concurrent.Future;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

/**
 * 异步任务-测试类
 * @author shiqiangqiang
 *
 */
@Component
public class AsyncTestTask {
	
	@Async
	public Future<Boolean> task1(){
		long startTime = System.currentTimeMillis();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long endTime = System.currentTimeMillis();
		System.out.println("任务1执行耗时：" + (endTime-startTime) + "毫秒");
		return new AsyncResult<>(true);
	}
	
	@Async
	public Future<Boolean> task2(){
		long startTime = System.currentTimeMillis();
		try {
			Thread.sleep(700);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long endTime = System.currentTimeMillis();
		System.out.println("任务2执行耗时：" + (endTime-startTime) + "毫秒");
		return new AsyncResult<>(true);
	}
	
	@Async
	public Future<Boolean> task3(){
		long startTime = System.currentTimeMillis();
		try {
			Thread.sleep(600);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long endTime = System.currentTimeMillis();
		System.out.println("任务3执行耗时：" + (endTime-startTime) + "毫秒");
		return new AsyncResult<>(true);
	}
	
	
}
