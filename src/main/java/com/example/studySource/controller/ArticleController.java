package com.example.studySource.controller;

import com.example.studySource.repository.ArticleRepository;
import com.example.studySource.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.studySource.model.request.*;

@Slf4j
@Controller
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ArticleService articleService;

    @PostMapping("/board/new/post")
    public String create(NewArticle newArticle){
        articleService.create(newArticle);
        return "redirect:/board";
    }
}