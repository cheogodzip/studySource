package com.example.studySource.controller;

import com.example.studySource.domain.article.Article;
import com.example.studySource.domain.article.ArticleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ArticleControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ArticleRepository articleRepository;

    @Test
    @Transactional
    public void 게시글_등록() throws Exception{
        //given
        String title = "title";
        String content = "content";
        String url = "http://localhost:" + port + "/board/new/post";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("title", title);
        map.add("content", content);
        map.add("writer", "writer");
        map.add("password", "password");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

        //when
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, request, String.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.FOUND);

        List<Article> all = articleRepository.findAll();
        assertThat(all.get(all.size()-1).getTitle()).isEqualTo(title);
        assertThat(all.get(all.size()-1).getContent()).isEqualTo(content);
    }

    @Test
    public void 게시글_수정() throws Exception{
        //given
        Article savedArticle = articleRepository.save(Article.builder()
                .title("title")
                .content("content")
                .writer("writer")
                .password("password")
                .build());

        System.out.println(savedArticle);

        Long updateId = savedArticle.getId();
        String exceptedTitle = "title2";
        String exceptedContent = "content2";

        String url = "http://localhost:" + port + "/board/modify/" + updateId;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("title", exceptedTitle);
        map.add("content", exceptedContent);
        map.add("password", "password");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

        //when
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, request, String.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.FOUND);

        List<Article> all = articleRepository.findAll();
        assertThat(all.get(all.size()-1).getTitle()).isEqualTo(exceptedTitle);
        assertThat(all.get(all.size()-1).getContent()).isEqualTo(exceptedContent);

        //delete
        articleRepository.deleteById(updateId);
    }
}
