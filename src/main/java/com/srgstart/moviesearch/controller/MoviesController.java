package com.srgstart.moviesearch.controller;

import com.srgstart.moviesearch.entity.Movie;
import com.srgstart.moviesearch.entity.MovieESEntity;
import com.srgstart.moviesearch.repository.ESRepository;
import com.srgstart.moviesearch.repository.MoviesRepository;
import com.srgstart.moviesearch.service.ESService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author srgstart
 * @create 2021/12/4 10:55
 * @Description TODO
 */
@RestController
@RequestMapping("/movies")
public class MoviesController {

    @Autowired
    private MoviesRepository moviesRepository;
    @Autowired
    private ESRepository esRepository;
    @Autowired
    private ESService esService;


    @GetMapping("/getMovieByName/{movieName}")
    public List<Movie> findMovie(@PathVariable String movieName) throws IOException {
        return esService.getMovieByName(movieName);
    }

    @GetMapping("/getAllMovie")
    public List<Movie> getAllMovie() {
        Iterable<Movie> movies = esRepository.findAll();
        List<Movie> movieList = new ArrayList<>();
        movies.forEach(movie -> movieList.add(movie));
        return movieList;
    }
}
