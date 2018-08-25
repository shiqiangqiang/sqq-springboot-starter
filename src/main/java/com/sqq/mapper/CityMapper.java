package com.sqq.mapper;

import java.util.List;

import com.sqq.domain.City;

public interface CityMapper {
    int deleteByPrimaryKey(Integer id);
    
    int deleteByCityCode(String cityCode);

    int insert(City record);
    
    int updateByPrimaryKey(City record);

    City selectByPrimaryKey(Integer id);
    
    List<City> selectByCityCode(String cityCode);

    List<City> selectAll();

}