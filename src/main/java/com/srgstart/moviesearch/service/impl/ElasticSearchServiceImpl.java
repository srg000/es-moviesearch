package com.srgstart.moviesearch.service.impl;

import com.srgstart.moviesearch.service.ElasticSearchService;
import com.srgstart.moviesearch.service.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author srgstart
 * @create 2021/11/25 12:59
 * @Description TODO
 */
@Service
@Slf4j
public class ElasticSearchServiceImpl implements ElasticSearchService {

    @Autowired
    private RestHighLevelClient elasticsearchClient;
    @Autowired
    private MovieService movieService;


    @Override
    public List<Map<String, Object>> searchPage(String keyword) throws IOException {

        log.info("要搜索的电影为:{}",keyword);

        // 条件查询
        SearchRequest searchRequest = new SearchRequest("movie_info");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();


        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("movie_name", keyword);
        sourceBuilder.query(matchQueryBuilder);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        // 高亮
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("movie_name");
        // 多个高亮显示！
        highlightBuilder.requireFieldMatch(false);
        highlightBuilder.preTags("<span style='color:red'>");
        highlightBuilder.postTags("</span>");
        sourceBuilder.highlighter(highlightBuilder);

        // 执行搜索
        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse = elasticsearchClient.search(searchRequest, RequestOptions.DEFAULT);

        // 解析结果
        ArrayList<Map<String, Object>> list = new ArrayList<>();
        SearchHit[] hits = searchResponse.getHits().getHits();
        log.info("搜索的"+keyword+"的信息：{}", Arrays.stream(hits).findAny());
        for (SearchHit hit : searchResponse.getHits().getHits()) {
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            HighlightField title = highlightFields.get("movie_name");
            // 原来的结果
            Map<String, Object> sourceAsMap =  hit.getSourceAsMap();

            log.info("======搜索的每一个"+keyword+"的信息：{}", hit.getSourceAsMap());

            // 解析高亮的字段，将原来的字段换为我们高亮的字段即可
            if (title != null) {
                Text[] fragments = title.fragments();
                String n_title = "";
                for (Text text : fragments) {
                    n_title += text;
                }
                // 高亮字段替换掉原来的内容即可
                sourceAsMap.put("movie_name",n_title);
            }

            list.add(sourceAsMap);
        }
        return list;
    }

    @Override
    public List<Map<String, Object>> main() throws IOException {

        // 条件查询
        SearchRequest searchRequest = new SearchRequest("movie_info");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();


        MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();
        sourceBuilder.query(matchAllQueryBuilder);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));


        // 执行搜索
        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse = elasticsearchClient.search(searchRequest, RequestOptions.DEFAULT);

        // 解析结果
        ArrayList<Map<String, Object>> list = new ArrayList<>();
        for (SearchHit hit : searchResponse.getHits().getHits()) {
            // 原来的结果
            Map<String, Object> sourceAsMap =  hit.getSourceAsMap();
            list.add(sourceAsMap);
        }

        return list;
    }
}
