package com.srgstart.moviesearch.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author srgstart
 * @create 2021/11/25 12:59
 * @Description TODO
 */
public interface ElasticSearchService {

    List<Map<String, Object>>  searchPage(String keyword) throws IOException;

    List<Map<String, Object>>  main() throws IOException;
}
