package com.example.studySource.controller;

import com.example.studySource.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.studySource.model.network.request.*;
import org.springframework.web.bind.annotation.PutMapping;

@Slf4j
@Controller
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping("/board/new/post")
    public String create(NewArticleRequest newArticle){
        articleService.create(newArticle);
        return "redirect:/board?page=1";
    }

    @PutMapping("/board/modify/{id}")
    public String modify(@PathVariable Long id, ModifyArticleRequest modifyArticleRequest){
        if(articleService.modify(id, modifyArticleRequest)){
            return "redirect:/board/articles/" + id;
        } else{
            return "board/passwordError";
        }
    }

    @DeleteMapping("/board/delete/{id}")
    public String delete(@PathVariable Long id, String password, Model model){

        model.addAttribute("id", id);

        if(articleService.delete(id, password)){
            return "redirect:/board?page=1";
        } else {
            return "board/passwordError";
        }
    }
}