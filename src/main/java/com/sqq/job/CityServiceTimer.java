package com.sqq.job;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.sqq.domain.City;
import com.sqq.service.CityService;

/**
 * 对城市定时任务具体类
 * @author shiqiangqiang
 *
 */
@Service
public class CityServiceTimer {
	private static Logger log = LoggerFactory.getLogger(CityServiceTimer.class);
	
	private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Autowired
	private CityService cityService;
	
	public void saveCity(){
		String currentTime = getCurrentTime();
		log.info("开始定时新增城市，当前时间：{}, 操作人：{}", currentTime, "Jack");
		City city = new City();
		city.setCityCode(currentTime);
		city.setCityName(currentTime);
		city.setUpdateTime(new Date());
		try {
			int count = cityService.saveCity(city);
		} catch (Exception e) {
			log.error("定时任务，新增城市出现异常，city:{}, exception:{}", city, e.getMessage());
		}
		log.info("结束定时新增城市，当前时间：{}, 操作人：{}, city:{}", currentTime, "Jack", city);
		return;
	}
	
	public void queryCity(){
		String currentTime = getCurrentTime();
		log.info("开始定时任务查询城市，当前时间：{}, 操作人：{}", currentTime, "Jack");
		List<City> cityList = new ArrayList<City>();
		try {
			cityList = cityService.queryCityListPaged(null, 1, 30);
			log.info("定时任务查询城市如下：\n cityListJson: {}", JSON.toJSON(cityList));
		} catch (Exception e) {
			log.error("定时任务查询城市出现异常，cityList:{}, exception:{}", cityList, e.getMessage());
		}
		return;
	}
	
	/**
	 * Description: 获取当前日期时间，格式yyyy-MM-dd hh24:mm:ss
	 * @return
	 * @author shiqiangqiang  
	 * @date 2018年8月25日
	 */
	private String getCurrentTime(){
		return simpleDateFormat.format(new Date());
	}
}
