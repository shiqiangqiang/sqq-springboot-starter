package com.sqq.mapper;

import com.sqq.domain.Hall;
import java.util.List;

public interface HallMapper {
    int deleteByPrimaryKey(Integer hallid);

    int insert(Hall record);

    Hall selectByPrimaryKey(Integer hallid);

    List<Hall> selectAll();

    int updateByPrimaryKey(Hall record);
}