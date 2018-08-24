package com.sqq.mapper;

import com.sqq.domain.Shows;
import java.util.List;

public interface ShowsMapper {
    int deleteByPrimaryKey(String showid);

    int insert(Shows record);

    Shows selectByPrimaryKey(String showid);

    List<Shows> selectAll();

    int updateByPrimaryKey(Shows record);
}