package com.sqq.mapper;

import com.sqq.domain.Movie;
import java.util.List;

public interface MovieMapper {
    int deleteByPrimaryKey(Integer filmid);

    int insert(Movie record);

    Movie selectByPrimaryKey(Integer filmid);

    List<Movie> selectAll();

    int updateByPrimaryKey(Movie record);
}