package com.example.studySource.controller.page;

import com.example.studySource.model.response.ArticlePageResponse;
import com.example.studySource.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/board/new")
    public String newArticle(){
        return "board/new";
    }

    @GetMapping("/board")
    public String search(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable, Model model){
        ArticlePageResponse articlePageResponse = articleService.search(pageable);

        model.addAttribute("articleInfoResponseList", articlePageResponse.getArticleInfoResponses());

        return "board/list";
    }
}
