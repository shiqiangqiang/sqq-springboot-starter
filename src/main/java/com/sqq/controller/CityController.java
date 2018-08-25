package com.sqq.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sqq.domain.City;
import com.sqq.service.CityService;
import com.sqq.util.BackJsonResult;

/**
 * 城市对外接口
 * @author shiqiangqiang
 *
 */
@RestController
@RequestMapping("/city")
public class CityController {
	private static Logger log = LoggerFactory.getLogger(CityController.class);
	
	private static final String MSG_SAVE_SUCCESS = "成功保存";
	private static final String MSG_SAVE_EXCEPTION = "出现异常，保存失败！";
	private static final String MSG_UPDATE_SUCCESS = "成功更新";
	private static final String MSG_UPDATE_EXCEPTION = "出现异常，保存失败！";
	private static final String MSG_DELETE_SUCCESS = "成功删除";
	private static final String MSG_DELETE_EXCEPTION = "出现异常，删除失败！";
	private static final String MSG_SELECT_SUCCESS = "成功查询";
	private static final String MSG_SELECT_EXCEPTION = "出现异常，查询失败！";
	private static final String MSG_SUFFIX = "条记录！";
	
	private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Autowired
	private CityService cityService;
	
	@RequestMapping("/saveCity")
	public BackJsonResult saveCity(){
		log.info("保存城市，当前时间：{}, 操作人：{}", getCurrentTime(), "Jack");
		City city = new City();
		city.setCityCode("test00001");
		city.setCityName("测试城市名称");
		int count = 0;
		try {
			count = cityService.saveCity(city);
		} catch (Exception e) {
			log.error("保存城市出现异常，city:{}, exception:{}", city, e.getMessage());
			return BackJsonResult.fail(MSG_SAVE_EXCEPTION);
		}
		return BackJsonResult.ok(MSG_SAVE_SUCCESS + count + MSG_SUFFIX);
	}
	
	@RequestMapping("/updateCity")
	public BackJsonResult updateCity(){
		log.info("更新城市，当前时间：{}, 操作人：{}", getCurrentTime(), "Jack");
		City city = new City();
		city.setId(8002);
		city.setCityCode("test00001");
		city.setCityName("修改-测试城市名称");
		int count = 0;
		try {
			count = cityService.changeCity(city);
		} catch (Exception e) {
			log.error("修改城市出现异常，city:{}, exception:{}", city, e.getMessage());
			return BackJsonResult.fail(MSG_UPDATE_EXCEPTION);
		}
		return BackJsonResult.ok(MSG_UPDATE_SUCCESS + count + MSG_SUFFIX);
	}
	
	@RequestMapping("/removeCity")
	public BackJsonResult removeCity(){
		log.info("删除城市，当前时间：{}, 操作人：{}", getCurrentTime(), "Jack");
		String cityCode = "test00001";
		int count = 0;
		try {
			count = cityService.removeCityById(8002);
		} catch (Exception e) {
			log.error("删除城市出现异常，cityCode:{}, exception:{}", cityCode, e.getMessage());
			return BackJsonResult.fail(MSG_DELETE_EXCEPTION);
		}
		return BackJsonResult.ok(MSG_DELETE_SUCCESS + count + MSG_SUFFIX);
	}
	
	@RequestMapping("/queryAllCity")
	public BackJsonResult queryAllCity(){
		log.info("查询城市，当前时间：{}, 操作人：{}", getCurrentTime(), "Jack");
		List<City> cityList = new ArrayList<City>();
		try {
			cityList = cityService.queryAllCity();
		} catch (Exception e) {
			log.error("查询城市出现异常，cityList:{}, exception:{}", cityList, e.getMessage());
			return BackJsonResult.fail(MSG_SELECT_EXCEPTION);
		}
		return BackJsonResult.ok(MSG_SELECT_SUCCESS + cityList.size() + MSG_SUFFIX, cityList);
	}
	
	/*@RequestMapping("/queryCityByCityCode")
	public BackJsonResult queryCityByCityCode(String cityCode){
		log.info("根据城市编码查询城市，当前时间：{}, 操作人：{}", getCurrentTime(), "Jack");
		List<City> cityList = new ArrayList<City>();
		try {
			cityList = cityService.queryCityByCityCode(cityCode);
		} catch (Exception e) {
			log.error("根据城市编码查询城市出现异常，cityList:{}, exception:{}", cityList, e.getMessage());
			return BackJsonResult.fail(MSG_SELECT_EXCEPTION);
		}
		return BackJsonResult.ok(MSG_SELECT_SUCCESS + cityList.size() + MSG_SUFFIX);
	}*/
	
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

