package com.festcampus.w10_project_board.board.controller;

import com.festcampus.w10_project_board.board.dto.ArticleDto;
import com.festcampus.w10_project_board.board.dto.response.ArticleResponse;
import com.festcampus.w10_project_board.board.dto.response.ArticleWithCommentsResponse;
import com.festcampus.w10_project_board.board.service.ArticleService;
import com.festcampus.w10_project_board.common.entity.constant.SearchType;
import com.festcampus.w10_project_board.common.service.PaginationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
@RequiredArgsConstructor
@RequestMapping("/articles")
@Controller
public class ArticleController {

    private final ArticleService articleService;
    private final PaginationService paginationService;

    @GetMapping
    public String articles(
            @RequestParam(required = false, name = "searchType") SearchType searchType,
            @RequestParam(required = false, name = "searchValue") String searchValue,
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC ) Pageable pageable,
            Model model
    ) {
        Page<ArticleResponse> articles = articleService.searchArticls(searchType, searchValue, pageable).map(ArticleResponse::from);
        List<Integer> barNumbers = paginationService.getPaginationBara(pageable.getPageNumber(), articles.getTotalPages());

        model.addAttribute("articles", articles);
        model.addAttribute("paginationBarNumbers", barNumbers);
        model.addAttribute("searTypes", SearchType.values());

        return "articles/list";
    }

    @GetMapping("/{articleId}")
    public String article(
            @PathVariable(name = "articleId") Long articleId,
            Model model
    ) {
        ArticleWithCommentsResponse article = ArticleWithCommentsResponse.from(articleService.getArticle(articleId));
        model.addAttribute("article", article);
        model.addAttribute("articlesComments", article.articleCommentsResponse());
        return "articles/detail";
    }

    @GetMapping("/search-hashtag")
    public String searchArticleHashtag(
            @RequestParam(required = false, name = "searchValue") String searchValue,
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC ) Pageable pageable,
            Model model
    ) {

        Page<ArticleResponse> articles = articleService.searchArticlesViaHashtag(searchValue, pageable).map(ArticleResponse::from);
        List<String> hashtags = articleService.getHashtags();
        List<Integer> barNumbers = paginationService.getPaginationBara(pageable.getPageNumber(), articles.getTotalPages());


        model.addAttribute("articles", articles);
        model.addAttribute("hashtags", hashtags);
        model.addAttribute("paginationBarNumbers", barNumbers);
        model.addAttribute("searchType", SearchType.HASHTAG);

        return "articles/search-hashtag";
    }

}
