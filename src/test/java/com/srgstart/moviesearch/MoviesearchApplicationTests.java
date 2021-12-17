package com.srgstart.moviesearch;

import com.srgstart.moviesearch.entity.Movie;
import com.srgstart.moviesearch.entity.MovieDO;
import com.srgstart.moviesearch.repository.ESRepository;
import com.srgstart.moviesearch.service.MovieService;
import org.apache.tomcat.util.http.fileupload.FileUtils;
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
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class MoviesearchApplicationTests {

    @Autowired
    private ESRepository esRepository;
    @Autowired
    private RestHighLevelClient elasticsearchClient;
    @Autowired
    private MovieService movieService;

    @Test
    void contextLoads() throws IOException {
        List<Movie> movies = new ArrayList<>();
        // 创建搜索请求
        SearchRequest searchRequest = new SearchRequest("movie");

        // 创建搜索对象
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.termQuery("name", "美人")) // 设置条件
                .sort("score", SortOrder.DESC) // 排序
                .from(0) // 起始条数（当前页-1）*size的值
                .size(10) // 每页展示条数
                .highlighter(new HighlightBuilder().field("*").requireFieldMatch(false).preTags("<span style='color:red'>").postTags("</span>")); // 高亮展示
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        searchRequest.source(sourceBuilder);

        // 执行搜索
        SearchResponse searchResponse = elasticsearchClient.search(searchRequest, RequestOptions.DEFAULT);


        SearchHit[] hits = searchResponse.getHits().getHits();
        for (SearchHit hit : hits) {
            // 原始文档
            System.out.println(hit.getSourceAsString());
            System.out.println("===========================");
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            Movie movie = new Movie();
            movie.setId(hit.getId());
            movie.setName(sourceAsMap.get("name").toString());
//            movie.setImg(sourceAsMap.get("img").toString());
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

        movies.forEach(movie -> System.out.println(movie));
    }

    @Test
    void save() {
        Movie movie = new Movie();
        movie.setId("asdfasdf");
        movie.setImg("https://meite-static.oss-cn-beijing.aliyuncs.com/movies/82.PNG");
        movie.setName("srgsrgsrg");
        movie.setScore(9.6);
        movie.setAwards("图灵奖");
        movie.setAbout("srg");
        movie.setScorePeople(9999L);
        movie.setXinxi("srg");
        movie.setUrl("asdfa");
        esRepository.save(movie);
    }








}
