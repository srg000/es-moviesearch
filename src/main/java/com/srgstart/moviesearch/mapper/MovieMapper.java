package com.srgstart.moviesearch.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.srgstart.moviesearch.entity.Movie;
import com.srgstart.moviesearch.entity.MovieDO;
import com.srgstart.moviesearch.entity.MovieInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author srgstart
 * @create 2021/11/25 11:22
 * @Description TODO
 */
@Mapper
public interface MovieMapper extends BaseMapper<MovieDO> {
}
