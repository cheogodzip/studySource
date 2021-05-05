package com.example.studySource.service;

import com.example.studySource.model.entity.Article;
import com.example.studySource.model.network.Pagination;
import com.example.studySource.model.network.request.ModifyArticleRequest;
import com.example.studySource.model.network.request.NewArticleRequest;
import com.example.studySource.model.network.response.ArticleInfoResponse;
import com.example.studySource.model.network.response.ArticlePageResponse;
import com.example.studySource.model.network.response.ArticleResponse;
import com.example.studySource.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public void create(NewArticleRequest newArticle) {

        // 제목, 내용 빈칸 에러 처리하기
        
        // 아이디, 비밀번호 2자 이상 에러 처리하기

        Article article = Article.builder()
                .title(newArticle.getTitle())
                .content(newArticle.getContent())
                .writer(newArticle.getWriter())
                .password(newArticle.getPassword())
                .createdAt(LocalDateTime.now())
                .build();

        log.info("article : {}", articleRepository.save(article));
    }

    public ArticleResponse read(Long id){
        return articleRepository.findById(id)
                .map(article -> {
                    return ArticleResponse.builder()
                            .id(article.getId())
                            .writer(article.getWriter())
                            .title(article.getTitle())
                            .content(article.getContent())
                            .createdAt(article.getCreatedAt())
                            .build();})
                .orElseGet(ArticleResponse::new);
    }

    public void modify(Long id, ModifyArticleRequest modifyArticleRequest) {

        Article article = articleRepository.findById(id).get();

        // 비밀번호 검사
        article.getPassword().equals(modifyArticleRequest.getPassword());
        // 제목, 내용 빈칸 에러 처리하기

        Article modifiedArticle = Article.builder()
                .title(modifyArticleRequest.getTitle())
                .content(modifyArticleRequest.getContent())
                .createdAt(LocalDateTime.now())
                .build();

        log.info("article : {}", articleRepository.save(modifiedArticle));
    }

    public boolean delete(Long id, String password){
        Article target = articleRepository.findById(id).get();

        if (target.getPassword().equals(password)){
            target.setDeleted(true);
            articleRepository.save(target);
            return true;
        } else{
            return false;
        }

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
    
    // 제목, 내용 빈칸 검사 메소드
}
