package com.example.studySource.domain.article;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ArticleRepositoryTest {

    @Autowired
    ArticleRepository articleRepository;

    @Test
    @Transactional
    public void 게시글저장_불러오기(){
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        articleRepository.save(Article.builder()
                .title(title)
                .content(content)
                .writer("shin@djdj.com")
                .password("password")
                .build());

        //when
        List<Article> articleList = articleRepository.findAll();

        //then
        Article article = articleList.get(articleList.size() - 1);
        assertThat(article.getTitle()).isEqualTo(title);
        assertThat(article.getContent()).isEqualTo(content);
    }

    @Test
    @Transactional
    public void BaseTimeEntity_등록(){
        //given
        LocalDateTime now = LocalDateTime.of(2021, 5, 18, 0, 0, 0);
        articleRepository.save(Article.builder()
                .title("title")
                .content("content")
                .writer("author")
                .password("password")
                .build());

        //when
        List<Article> articleList = articleRepository.findAll();

        //then
        Article article = articleList.get(articleList.size() - 1);

        System.out.println(">>>>>>>>>>>> createDate="+article.getContent() + ", updatedDate=" + article.getUpdatedAt());

        assertThat(article.getCreatedAt()).isAfter(now);
        assertThat(article.getUpdatedAt()).isAfter(now);
    }
}
