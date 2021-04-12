package com.example.studySource.controller;

import com.example.studySource.model.entity.Article;
import com.example.studySource.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.studySource.model.request.*;

import java.time.LocalDateTime;

@Slf4j
@Controller
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    @PostMapping("/board/new/post")
    public String create(NewArticle newArticle){
        log.info(newArticle.toString());
        Article article = Article.builder()
                .title(newArticle.getTitle())
                .content(newArticle.getContent())
                .writer(newArticle.getWriter())
                .password(newArticle.getPassword())
                .createdAt(LocalDateTime.now())
                .build();

        System.out.println(articleRepository.save(article));

        return "redirect:/board";
    }


}