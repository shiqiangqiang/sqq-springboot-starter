package com.sqq.timedtask;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.sqq.job.CityServiceTimer;

/**
 * 定时任务-测试类
 * @author shiqiangqiang
 *
 */
@Component
public class TestTask {
	private static final Logger log = LoggerFactory.getLogger(TestTask.class);
	private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Autowired
	private CityServiceTimer cityServiceTimer;
	
	/**
	 * Description: 定义每3秒输出当前时间
	 * @author shiqiangqiang  
	 * @date 2018年8月26日
	 */
//	@Scheduled(fixedRate=60000)
	public void reportCurrentTime(){
		String currentTime = simpleDateFormat.format(new Date());
		log.info("currentTime: " + currentTime);
	}
	
	/**
	 * Description: 定义每30秒新增一条City记录
	 * @author shiqiangqiang  
	 * @date 2018年8月26日
	 */
//	@Scheduled(fixedRate=30000)
	public void addUserFixedRate(){
		cityServiceTimer.saveCity();
	}
	
	/**
	 * Description: 每周日21:39执行查询city表前30条记录
	 * cron:6位，秒 分 时 日 月 周
	 * @author shiqiangqiang  
	 * @date 2018年8月26日
	 */
	@Scheduled(cron="0 39 21 * * 7")
	public void addTestFixedTime(){
		cityServiceTimer.queryCity();
	}
	
}
