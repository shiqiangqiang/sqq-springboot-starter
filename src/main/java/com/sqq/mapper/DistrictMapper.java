package com.sqq.mapper;

import com.sqq.domain.District;
import java.util.List;

public interface DistrictMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(District record);

    District selectByPrimaryKey(Integer id);

    List<District> selectAll();

    int updateByPrimaryKey(District record);
}