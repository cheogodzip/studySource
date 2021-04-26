package com.example.studySource.controller;

import com.example.studySource.model.entity.Article;
import com.example.studySource.model.response.ArticleIndexResponse;
import com.example.studySource.repository.ArticleRepository;
import com.example.studySource.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.studySource.model.request.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<ArticleIndexResponse> search(@PageableDefault(sort = "id", direction = Sort.Direction.ASC)Pageable pageable){
        Page<Article> articles = articleRepository.findAll(pageable);
        List<ArticleIndexResponse> articleIndexResponseList = articles.stream()
                .map(article -> {
                    ArticleIndexResponse articleIndexResponse = ArticleIndexResponse.builder()
                            .id(article.getId())
                            .title(article.getTitle())
                            .writer(article.getWriter())
                            .createdAt(article.getCreatedAt())
                            .build();
                    return articleIndexResponse;
                })
                .collect(Collectors.toList());

        return articleIndexResponseList;

    }

}