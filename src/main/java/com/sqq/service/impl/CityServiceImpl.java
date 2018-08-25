package com.sqq.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sqq.domain.City;
import com.sqq.mapper.CityMapper;
import com.sqq.service.CityService;
@Service
public class CityServiceImpl implements CityService {
	
	@Autowired
	private CityMapper cityMapper;

	@Override
	public int saveCity(City city) throws Exception {
		return cityMapper.insert(city);
	}

	@Override
	public int changeCity(City city) throws Exception {
		return cityMapper.updateByPrimaryKey(city);
	}

	@Override
	public int removeCityById(Integer id) throws Exception {
		return cityMapper.deleteByPrimaryKey(id);
	}
	
	@Override
	public List<City> queryCityByCityCode(String cityCode) throws Exception {
		return cityMapper.selectByCityCode(cityCode);
	}

	@Override
	public List<City> queryAllCity() throws Exception {
		return cityMapper.selectAll();
	}

	@Override
	public int removeCityByCityCode(String cityCode) throws Exception {
		return cityMapper.deleteByCityCode(cityCode);
	}

}
