package com.srgstart.moviesearch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author srgstart
 * @Create 2021/4/14 17:45
 * @Description TODO
 */
@Controller
public class IndexController {

    @GetMapping({"/"})
    public String searchIndex() {
        return "searchIndex";
    }

    @GetMapping({"/index"})
    public String index() {
        return "index";
    }



}
