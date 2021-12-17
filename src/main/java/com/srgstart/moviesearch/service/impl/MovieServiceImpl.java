package com.srgstart.moviesearch.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.srgstart.moviesearch.entity.Movie;
import com.srgstart.moviesearch.entity.MovieDO;
import com.srgstart.moviesearch.entity.MovieInfo;
import com.srgstart.moviesearch.mapper.MovieMapper;
import com.srgstart.moviesearch.service.MovieService;
import org.springframework.stereotype.Service;

/**
 * @author srgstart
 * @create 2021/11/25 11:18
 * @Description TODO
 */
@Service
public class MovieServiceImpl extends ServiceImpl<MovieMapper, MovieDO> implements MovieService {

}
