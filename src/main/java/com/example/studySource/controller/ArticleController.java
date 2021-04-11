package com.example.studySource.controller;

import com.example.studySource.model.entity.DTO.NewArticle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class ArticleController {

    @PostMapping("/board/new/post")
    public String create(NewArticle newArticle){
        log.info(newArticle.toString());
        return "redirect:/board";
    }
}