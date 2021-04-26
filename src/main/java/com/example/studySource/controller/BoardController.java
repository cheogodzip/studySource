package com.example.studySource.controller;

import com.example.studySource.model.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/board")
    public String index(){

        return "board/index";
    }

    @GetMapping("/board/new")
    public String newArticle(){
        return "board/new";
    }

}
