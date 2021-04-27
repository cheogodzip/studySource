package com.example.studySource.service;

import com.example.studySource.model.entity.Article;
import com.example.studySource.model.network.Pagination;
import com.example.studySource.model.request.NewArticle;
import com.example.studySource.model.response.ArticleInfoResponse;
import com.example.studySource.model.response.ArticlePageResponse;
import com.example.studySource.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
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

    public ArticlePageResponse search(Pageable pageable){

        Page<Article> articles = articleRepository.findAll(pageable);

        List<ArticleInfoResponse> articleInfoResponseList = articles.stream()
                .map(article -> {
                    ArticleInfoResponse articleInfoResponse = ArticleInfoResponse.builder()
                            .id(article.getId())
                            .title(article.getTitle())
                            .writer(article.getWriter())
                            .createdAt(article.getCreatedAt())
                            .build();
                    return articleInfoResponse;
                })
                .collect(Collectors.toList());

        Pagination pagination = Pagination.builder()
                .totalPages(articles.getTotalPages())
                .totalElements(articles.getTotalElements())
                .currentPage(articles.getNumber())
                .currentElemets(articles.getNumberOfElements())
                .build();

        log.info("pagination : {}", pagination);

        return new ArticlePageResponse(articleInfoResponseList, pagination);
    }
}
