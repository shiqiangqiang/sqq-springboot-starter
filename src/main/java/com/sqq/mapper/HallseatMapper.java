package com.sqq.mapper;

import com.sqq.domain.Hallseat;
import java.util.List;

public interface HallseatMapper {
    int deleteByPrimaryKey(String seatno);

    int insert(Hallseat record);

    Hallseat selectByPrimaryKey(String seatno);

    List<Hallseat> selectAll();

    int updateByPrimaryKey(Hallseat record);
}