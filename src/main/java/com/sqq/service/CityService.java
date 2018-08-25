package com.sqq.service;

import java.util.List;

import com.sqq.domain.City;

public interface CityService {
	int saveCity(City city) throws Exception;
	
	int changeCity(City city) throws Exception;
	
	int removeCityById(Integer id) throws Exception;
	
//	int removeCityByCityCode(String cityCode) throws Exception;
	
//	List<City> queryCityByCityCode(String cityCode) throws Exception;
	
	List<City> queryAllCity() throws Exception;
	
}
