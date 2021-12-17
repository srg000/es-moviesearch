package com.srgstart.moviesearch.repository;

import com.srgstart.moviesearch.entity.Movie;
import com.srgstart.moviesearch.entity.MovieESEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;


/**
 * @author srgstart
 * @create 2021/12/4 10:54
 * @Description TODO
 */
public interface ESRepository extends ElasticsearchRepository<Movie, Integer> {
    Movie getByName(String name);


}
