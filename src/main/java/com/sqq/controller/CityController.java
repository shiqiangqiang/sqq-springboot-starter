package com.sqq.controller;

import com.sqq.domain.City;
import com.sqq.service.CityService;
import com.sqq.util.BackJsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 城市对外接口
 * @author shiqiangqiang
 *
 */
@RestController
@RequestMapping("/api/city")
public class CityController {
	private static Logger log = LoggerFactory.getLogger(CityController.class);
	
	private static final String MSG_SAVE_SUCCESS = "成功保存";
	private static final String MSG_SAVE_EXCEPTION = "出现异常，保存失败！";
	private static final String MSG_UPDATE_SUCCESS = "成功更新";
	private static final String MSG_UPDATE_EXCEPTION = "出现异常，更新失败！";
	private static final String MSG_DELETE_SUCCESS = "成功删除";
	private static final String MSG_DELETE_EXCEPTION = "出现异常，删除失败！";
	private static final String MSG_SELECT_SUCCESS = "成功查询";
	private static final String MSG_SELECT_EXCEPTION = "出现异常，查询失败！";
	private static final String MSG_SUFFIX = "条记录！";
	
	private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Autowired
	private CityService cityService;
	
	@PostMapping("addCity")
	public BackJsonResult saveCity(@RequestBody City city){
		log.info("保存城市，当前时间：{}, 操作人：{}, city: {}", getCurrentTime(), "Jack", city);
		int count = 0;
		try {
			count = cityService.saveCity(city);
		} catch (Exception e) {
			log.error("保存城市出现异常，city:{}, exception:{}", city, e.getMessage());
			return BackJsonResult.fail(MSG_SAVE_EXCEPTION);
		}
		return BackJsonResult.ok(MSG_SAVE_SUCCESS + count + MSG_SUFFIX);
	}
	
	@PostMapping("/updateCity")
	public BackJsonResult updateCity(@RequestBody City city){
		log.info("更新城市，当前时间：{}, 操作人：{}, city: {}", getCurrentTime(), "Jack", city);
		int count = 0;
		try {
			city = cityService.queryById(city.getId());
			if (city == null){
				log.info("id为{}的城市不存在！", city.getId());
				return BackJsonResult.fail(MSG_UPDATE_EXCEPTION);
			}
			city.setCityName("修改-测试城市名称--4");
			city.setAcronym("ceshichengshi");
			city.setUpdateTime(new Date());
			count = cityService.changeCity(city);
		} catch (Exception e) {
			log.error("修改城市出现异常, exception:{}", e.getMessage());
			return BackJsonResult.fail(MSG_UPDATE_EXCEPTION);
		}
		return BackJsonResult.ok(MSG_UPDATE_SUCCESS + count + MSG_SUFFIX);
	}
	
	@GetMapping("/removeCity")
	public BackJsonResult removeCity(@RequestParam Integer id){
		log.info("删除城市，当前时间：{}, 操作人：{}, id:{}", getCurrentTime(), "Jack", id);
		int count = 0;
		try {
			count = cityService.removeCityById(id);
		} catch (Exception e) {
			log.error("删除城市出现异常，id:{}, exception:{}", id, e.getMessage());
			return BackJsonResult.fail(MSG_DELETE_EXCEPTION);
		}
		return BackJsonResult.ok(MSG_DELETE_SUCCESS + count + MSG_SUFFIX);
	}
	
	@RequestMapping("/queryCityById")
	public BackJsonResult queryCityById(Integer id){
		log.info("根据城市编码查询城市，当前时间：{}, 操作人：{}", getCurrentTime(), "Jack");
		List<City> cityList = new ArrayList<City>();
		try {
			City city = cityService.queryById(id);
			cityList.add(city);
		} catch (Exception e) {
			log.error("根据城市编码查询城市出现异常，cityList:{}, exception:{}", cityList, e.getMessage());
			return BackJsonResult.fail(MSG_SELECT_EXCEPTION);
		}
		return BackJsonResult.ok(MSG_SELECT_SUCCESS + cityList.size() + MSG_SUFFIX, cityList);
	}

	/**
	 * 分页查询城市列表
	 * @param pageNum
	 * @return
	 */
	@GetMapping("/queryCityListPaged")
	public BackJsonResult queryCityListPaged(@RequestParam("pageNum") Integer pageNum){
		if (pageNum == null){
			pageNum = 1;
		}
		int pageSize = 10;
		log.info("分页查询城市列表，当前时间：{}, 操作人：{}", getCurrentTime(), "Jack");
		List<City> cityList = new ArrayList<City>();
		City city = null;
		try {
			cityList = cityService.queryCityListPaged(city, pageNum, pageSize);
		} catch (Exception e) {
			log.error("分页查询城市出现异常，pageNum:{}, pageSize:{}, cityList:{}, exception:{}", pageNum, pageSize, cityList, e.getMessage());
			return BackJsonResult.fail(MSG_SELECT_EXCEPTION);
		}
		return BackJsonResult.ok(MSG_SELECT_SUCCESS + cityList.size() + MSG_SUFFIX, cityList);
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

