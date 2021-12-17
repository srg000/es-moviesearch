package com.srgstart.moviesearch.repository;

import com.srgstart.moviesearch.entity.MovieESEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;


/**
 * @author srgstart
 * @create 2021/12/4 10:54
 * @Description TODO
 */
public interface MoviesRepository extends ElasticsearchRepository<MovieESEntity, Long> {
    /**
     * 根据电影名字查询，不支持模糊
     * @param movieName 要查询的电影
     * @return 返回查询到的电影，如果没有，返回null
     */
    List<MovieESEntity> getByMovieName(String movieName);

    /**
     * 根据电影名字模糊查询
     * @param movieName 要查询的电影
     * @return 返回查询到的电影，如果没有，返回null
     */
    List<MovieESEntity> getByMovieNameIn(String movieName);


}
