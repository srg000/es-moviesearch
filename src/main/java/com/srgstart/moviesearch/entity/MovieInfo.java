package com.srgstart.moviesearch.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author srgstart
 * @create 2021/11/25 11:09
 * @Description 电影实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "movie_info")
public class MovieInfo {
    /**
     * 电影id
     */
    private Integer id;
    /**
     * 电影名
     */
    private String movieName;
    /**
     * 电影图片URL
     */
    private String moviePic;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 商品录入时间
     */
    private Date createTime;

    /**
     * 最后修改时间
     */
    private Date updateTime;

    /**
     * 逻辑删除(1:已删除，0:未删除)
     */
    private Integer isDeleted;
}
