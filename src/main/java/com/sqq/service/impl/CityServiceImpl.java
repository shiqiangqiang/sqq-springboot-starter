package com.sqq.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.sqq.domain.City;
import com.sqq.mapper.CityMapper;
import com.sqq.service.CityService;
@Service
public class CityServiceImpl implements CityService {
	
	@Autowired
	private CityMapper cityMapper;

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int saveCity(City city) throws Exception {
		return cityMapper.insert(city);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int changeCity(City city) throws Exception {
		return cityMapper.updateByPrimaryKeySelective(city);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int removeCityById(Integer id) throws Exception {
		return cityMapper.deleteByPrimaryKey(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public City queryById(Integer id) throws Exception {
		return cityMapper.selectByPrimaryKey(id);
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
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
