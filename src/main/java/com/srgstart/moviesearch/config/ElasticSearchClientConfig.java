package com.srgstart.moviesearch.config;


import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

/**
 * @author srgstart
 * @create 2021/11/25 11:07
 * @Description 连接es的配置
 */
@Configuration
public class ElasticSearchClientConfig extends AbstractElasticsearchConfiguration {

    /**
     * 这个client 用来替换 transportClient（9300）对象
     * @return 返回生成的client对象
     */
    @Override
    @Bean
    public RestHighLevelClient elasticsearchClient() {

        // 定义客户端对象
        final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo("112.124.34.242:9200")
                .build();

        // 通过RestClients对象创建
        return RestClients.create(clientConfiguration).rest();
    }

}
