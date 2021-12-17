package com.srgstart.moviesearch.controller;

import com.srgstart.moviesearch.entity.MovieInfo;
import com.srgstart.moviesearch.service.ElasticSearchService;
import com.srgstart.moviesearch.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author srgstart
 * @create 2021/11/25 11:08
 * @Description TODO
 */
@RestController("/")
public class ContentController {
    @Autowired
    private MovieService movieService;


    @Autowired
    private ElasticSearchService elasticSearchService;

//    @GetMapping("/getAllMovieInfo")
//    public List<MovieInfo> getAllMovie() {
//        return movieService.list();
//    }

    @GetMapping("/search/{keyword}")
    public List<Map<String, Object>> search(@PathVariable("keyword") String keyword) throws IOException {
        return elasticSearchService.searchPage(keyword);
    }

    @GetMapping("/main")
    public List<Map<String, Object>> main() throws IOException {
        return elasticSearchService.main();
    }

}
