package com.srgstart.moviesearch;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Repository;

/**
 * @author 宋仁刚
 */
@SpringBootApplication
@MapperScan("com.srgstart.moviesearch.mapper")
public class MoviesearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoviesearchApplication.class, args);
    }

}
