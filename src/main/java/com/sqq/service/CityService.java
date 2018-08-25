package com.sqq.service;

import java.util.List;

import com.sqq.domain.City;

public interface CityService {
	int saveCity(City city) throws Exception;
	
	int changeCity(City city) throws Exception;
	
	int removeCityById(Integer id) throws Exception;
	
//	int removeCityByCityCode(String cityCode) throws Exception;
	
	City queryById(Integer id) throws Exception;
	
	/**
	 * Description: 分页查询City列表
	 * @param city  
	 * @param pageNum  页号
	 * @param pageSize  一页显示记录数
	 * @return
	 * @throws Exception
	 * @author shiqiangqiang  
	 * @date 2018年8月25日
	 */
	List<City> queryCityListPaged(City city, Integer pageNum, Integer pageSize) throws Exception;
}
