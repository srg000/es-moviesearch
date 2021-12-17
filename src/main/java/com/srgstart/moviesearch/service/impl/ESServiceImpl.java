package com.srgstart.moviesearch.service.impl;

import com.srgstart.moviesearch.entity.Movie;
import com.srgstart.moviesearch.repository.ESRepository;
import com.srgstart.moviesearch.service.ESService;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author srgstart
 * @create 2021/12/5 13:43
 * @Description TODO
 */
@Service
public class ESServiceImpl implements ESService {

    @Autowired
    private ESRepository esRepository;
    @Autowired
    private RestHighLevelClient elasticsearchClient;

    @Override
    public List<Movie> getMovieByName(String name) throws IOException {

        if ("null".equals(name)) {
            int random = (int) (Math.random()*27);

            List<Movie> movies = new ArrayList<>();
            // 创建搜索请求
            SearchRequest searchRequest = new SearchRequest("movie");

            // 创建搜索对象
            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
            // 设置条件
            sourceBuilder.query(QueryBuilders.matchAllQuery())
                    // 排序
                    .sort("score", SortOrder.DESC)
                    // 起始条数（当前页-1）*size的值
                    .from(random)
                    // 每页展示条数
                    .size(20);

            searchRequest.source(sourceBuilder);

            // 执行搜索
            SearchResponse searchResponse = null;
            searchResponse = elasticsearchClient.search(searchRequest, RequestOptions.DEFAULT);


            SearchHit[] hits = searchResponse.getHits().getHits();
            for (SearchHit hit : hits) {
                Map<String, Object> sourceAsMap = hit.getSourceAsMap();
                Movie movie = new Movie();
                movie.setId(hit.getId());
                movie.setName(sourceAsMap.get("name").toString());
                movie.setImg(sourceAsMap.get("img").toString());
                movie.setAbout(sourceAsMap.get("about").toString());
                movie.setScore(Double.parseDouble(sourceAsMap.get("score").toString()));
                movie.setScorePeople(Long.parseLong(sourceAsMap.get("scorepeople").toString()));
                movie.setUrl(sourceAsMap.get("url").toString());
                movie.setXinxi(sourceAsMap.get("xinxi").toString());
                if (sourceAsMap.get("awards") != null) {
                    movie.setAwards(sourceAsMap.get("awards").toString());
                }

                movies.add(movie);
            }

            return movies;
        }

        List<Movie> movies = new ArrayList<>();
        // 创建搜索请求
        SearchRequest searchRequest = new SearchRequest("movie");

        // 创建搜索对象
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        // 设置条件
        sourceBuilder.query(QueryBuilders.termQuery("name", name))
                // 排序
                .sort("score", SortOrder.DESC)
                // 起始条数（当前页-1）*size的值
                .from(0)
                // 每页展示条数
                .size(10)
                // 高亮展示
                .highlighter(new HighlightBuilder().field("*").requireFieldMatch(false).preTags("<b style='color:red;font-size: 18px'>").postTags("</b>"));
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        searchRequest.source(sourceBuilder);

        // 执行搜索
        SearchResponse searchResponse = null;
        searchResponse = elasticsearchClient.search(searchRequest, RequestOptions.DEFAULT);


        SearchHit[] hits = searchResponse.getHits().getHits();
        for (SearchHit hit : hits) {
            // 原始文档
            System.out.println(hit.getSourceAsString());
            System.out.println("===========================");
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            Movie movie = new Movie();
            movie.setId(hit.getId());
            movie.setName(sourceAsMap.get("name").toString());
            movie.setImg(sourceAsMap.get("img").toString());
            System.out.println(sourceAsMap.get("awards"));

            System.out.println("==================");
            movie.setAbout(sourceAsMap.get("about").toString());
            movie.setScore(Double.parseDouble(sourceAsMap.get("score").toString()));
            movie.setScorePeople(Long.parseLong(sourceAsMap.get("scorepeople").toString()));
            movie.setUrl(sourceAsMap.get("url").toString());
            movie.setXinxi(sourceAsMap.get("xinxi").toString());
            movie.setAwards(sourceAsMap.get("awards").toString());

            // 高亮字段
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            if (highlightFields.containsKey("name")) {
                movie.setName(highlightFields.get("name").fragments()[0].toString());
            }

            if (highlightFields.containsKey("about")) {
                movie.setAbout(highlightFields.get("about").fragments()[0].toString());
            }


            movies.add(movie);
        }

        movies.forEach(movie -> {
            System.out.println(movie);
        });
        return movies;
    }

}
