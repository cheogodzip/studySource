package com.example.studySource.controller.page;

import com.example.studySource.model.network.response.ArticlePageResponse;
import com.example.studySource.model.network.response.ArticleResponse;
import com.example.studySource.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;

@Slf4j
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

    @GetMapping("/board/modify/{id}")
    public String modifyArticle(@PathVariable Long id, Model model){
        ArticleResponse articleResponse = articleService.read(id);
        model.addAttribute("article", articleResponse);
        return "board/modify";
    }

    // 아티클 리스트
    @GetMapping("/board")
    public String search(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable, Model model){
        ArticlePageResponse articlePageResponse = articleService.search(pageable);

        model.addAttribute("articleInfoResponseList", articlePageResponse.getArticleInfoResponses());

        ArrayList pageIndex = new ArrayList();
        for (int i=0; i < articlePageResponse.getPagination().getTotalPages(); i++) pageIndex.add(i);
        model.addAttribute("pageIndex", pageIndex);

        return "board/list";
    }

    // 상세 보기
    @GetMapping("/board/articles/{id}")
    public String read(@PathVariable Long id, Model model){
        ArticleResponse articleResponse = articleService.read(id);

        model.addAttribute("article", articleResponse);

        return "board/article";
    }

    @GetMapping("/board/delete/{id}")
    public String delete(@PathVariable Long id, Model model){
        model.addAttribute("id", id);
        return "board/delete";
    }
}