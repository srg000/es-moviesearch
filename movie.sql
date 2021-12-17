CREATE TABLE `movie_info` (
      `id` bigint(20) UNSIGNED  NOT NULL AUTO_INCREMENT COMMENT '电影ID',
      `movie_name` varchar(50) NOT NULL COMMENT '电影名',
      `movie_pic` VARCHAR(200) NOT NULL COMMENT '电影图片URL',
      `status` tinyint(3) UNSIGNED  NOT NULL DEFAULT '0' COMMENT '状态',
      `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
      `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
      `is_deleted` tinyint(3) NOT NULL DEFAULT '0' COMMENT '逻辑删除(1:已删除，0:未删除)',
      PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='电影信息表';