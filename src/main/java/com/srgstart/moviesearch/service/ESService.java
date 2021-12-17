package com.srgstart.moviesearch.service;

import com.srgstart.moviesearch.entity.Movie;

import java.io.IOException;
import java.util.List;

/**
 * @author srgstart
 * @create 2021/12/5 13:42
 * @Description TODO
 */
public interface ESService {

    /**
     * 根据电影名，高亮，分词，模糊查询
     * @param name 要查询的电影名
     * @return 返回查询到电影集合
     * @throws IOException 抛出的IO异常
     */
    List<Movie> getMovieByName(String name) throws IOException;
}
