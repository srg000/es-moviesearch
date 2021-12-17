package com.srgstart.moviesearch.entity;

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
@Document(indexName = "movie")
public class Movie {
    /**
     * 电影id
     */
    @Id
    private String id;
    /**
     * 电影概述
     */
    @Field(value = "about", type = FieldType.Text, analyzer = "ik_max_word")
    private String about;
    /**
     * 电影获奖信息
     */
    @Field(value = "awards", type = FieldType.Keyword)
    private String awards;
    /**
     * 电影名
     */
    @Field(value = "name", type = FieldType.Text, analyzer = "ik_max_word")
    private String name;
    /**
     * 电影评分
     */
    @Field(value = "score", type = FieldType.Double)
    private Double score;
    /**
     * 评价人数
     */
    @Field(value = "scorepeople", type = FieldType.Long)
    private Long scorePeople;
    /**
     * 电影链接
     */
    @Field(value = "url", type = FieldType.Keyword)
    private String url;
    /**
     * 电影图片
     */
    @Field(value = "img", type = FieldType.Keyword)
    private String img;
    /**
     * 导演信息
     */
    @Field(value = "xinxi", type = FieldType.Keyword)
    private String xinxi;
}
