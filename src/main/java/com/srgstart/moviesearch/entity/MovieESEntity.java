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
 * @create 2021/12/4 9:01
 * @Description TODO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "movie_info")
public class MovieESEntity {
    @Id
    private Long id;
    @Field(value = "movie_name", type = FieldType.Text, analyzer = "ik_max_word")
    private String movieName;
    @Field(value = "movie_pic", type = FieldType.Keyword)
    private String moviePic;
}
