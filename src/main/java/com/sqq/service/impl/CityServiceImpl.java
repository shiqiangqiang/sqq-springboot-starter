package com.sqq.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.sqq.domain.City;
import com.sqq.domain.CityExample;
import com.sqq.domain.DistrictExample;
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
		return cityMapper.updateByPrimaryKeySelective(city);
	}

	@Override
	public int removeCityById(Integer id) throws Exception {
		return cityMapper.deleteByPrimaryKey(id);
	}
	
	@Override
	public City queryById(Integer id) throws Exception {
		return cityMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<City> queryCityListPaged(City city, Integer pageNum,
			Integer pageSize) throws Exception {
		// 开始分页
		PageHelper.startPage(pageNum, pageSize);
//		CityExample example = new CityExample();
//		CityExample.Criteria criteria = example.createCriteria(); 
//		if (!StringUtils.isEmpty(city.getUpdateTime())){
//			// 设置按updateTime降序排列
//			criteria.andUpdateTimeEqualTo(city.getUpdateTime());
//		}
//		// 设置排序
//		example.setOrderByClause("locationId desc");
//		List<City> cityList = cityMapper.selectByExample(example);
		List<City> cityList = cityMapper.selectAll();
		return cityList;
	}

	
}
