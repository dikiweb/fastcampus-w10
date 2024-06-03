package com.festcampus.w10_project_board.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * packageName   : com.festcampus.w10_project_board.board.controller
 * fileName     : ArticleController
 * author       : danny
 * date         : 2024-06-02
 * description  :
 * ===========================================================
 * DATE             AUTHOR          NOTE
 * -----------------------------------------------------------
 * 2024-06-02          danny         최초 생성
 */
@RequestMapping("/articles")
@Controller
public class ArticleController {

    @GetMapping
    public String articles(Model model) {
        model.addAttribute("articles", List.of());
        return "articles/list";
    }

    @GetMapping("/{articleId}")
    public String article(@PathVariable(name = "articleId") Long articleId,  Model model) {
        model.addAttribute("article", "article");  // TODO: 실제 구현할 때 Data Binding 해 줘야 합니다.
        model.addAttribute("articlesComments", List.of());
        return "articles/detail";
    }
}
