package com.srgstart.moviesearch.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author srgstart
 * @create 2021/12/4 16:30
 * @Description TODO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "movie")
public class MovieDO {
    /**
     * 电影id
     */
    private String id;
    /**
     * 电影概述
     */
    private String about;
    /**
     * 电影获奖信息
     */
    private String awards;
    /**
     * 电影名
     */
    private String name;
    /**
     * 电影评分
     */
    private Double score;
    /**
     * 评价人数
     */
    private Long scorepeople;
    /**
     * 电影链接
     */
    private String url;
    /**
     * 电影图片
     */
    private String img;
    /**
     * 导演信息
     */
    private String xinxi;
}
