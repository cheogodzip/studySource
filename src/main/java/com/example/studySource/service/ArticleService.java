package com.example.studySource.service;

import com.example.studySource.model.entity.Article;
import com.example.studySource.model.request.NewArticle;
import com.example.studySource.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@Slf4j
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public void create(NewArticle newArticle) {

        Article article = Article.builder()
                .title(newArticle.getTitle())
                .content(newArticle.getContent())
                .writer(newArticle.getWriter())
                .password(newArticle.getPassword())
                .createdAt(LocalDateTime.now())
                .build();

        log.info("article : {}", articleRepository.save(article));
    }
}
