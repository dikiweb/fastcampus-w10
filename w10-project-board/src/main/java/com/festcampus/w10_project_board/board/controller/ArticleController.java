package com.festcampus.w10_project_board.board.controller;

import com.festcampus.w10_project_board.board.dto.request.ArticleRequest;
import com.festcampus.w10_project_board.board.dto.response.ArticleResponse;
import com.festcampus.w10_project_board.board.dto.response.ArticleWithCommentsResponse;
import com.festcampus.w10_project_board.board.service.ArticleService;
import com.festcampus.w10_project_board.common.entity.constant.FormStatus;
import com.festcampus.w10_project_board.common.entity.constant.SearchType;
import com.festcampus.w10_project_board.common.service.PaginationService;
import com.festcampus.w10_project_board.userAccount.dto.security.SitePrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String listArticles(
            @RequestParam(required = false) SearchType searchType,
            @RequestParam(required = false) String searchValue,
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
            Model model
    ) {
        Page<ArticleResponse> articles = articleService.searchArticles(searchType, searchValue, pageable).map(ArticleResponse::from);
        List<Integer> barNumbers = paginationService.getPaginationBara(pageable.getPageNumber(), articles.getTotalPages());

        model.addAttribute("seoAuthor", "By Daniel Park (Park jong chul)");
        model.addAttribute("seoGenerator", "By IntelliJ IDEA");
        model.addAttribute("seoDescript", "SEO  사이트 설명 입력할 곳");
        model.addAttribute("seoKeyword", "SEO 키워드 입력할 곳");

        model.addAttribute("title", "게시판 목록");

        model.addAttribute("articles", articles);
        model.addAttribute("paginationBarNumbers", barNumbers);
        model.addAttribute("searTypes", SearchType.values());
        model.addAttribute("searchTypeHashtag", SearchType.HASHTAG);

        return "articles/list";
    }

    @GetMapping("/{articleId}")
    public String detailArticle(
            @PathVariable Long articleId,
            Model model
    ) {
        ArticleWithCommentsResponse article = ArticleWithCommentsResponse.from(articleService.getArticleWithComments(articleId));

        model.addAttribute("seoAuthor", "By Daniel Park (Park jong chul)");
        model.addAttribute("seoGenerator", "By IntelliJ IDEA");
        model.addAttribute("seoDescript", "SEO  사이트 설명 입력할 곳");
        model.addAttribute("seoKeyword", "SEO 키워드 입력할 곳");

        model.addAttribute("title", "게시판 상세보기 : " + article.title());

        model.addAttribute("article", article);
        model.addAttribute("articlesComments", article.articleCommentsResponse());
        model.addAttribute("totalCount", articleService.getArticleCount());
        model.addAttribute("searchTypeHashtag", SearchType.HASHTAG);

        return "articles/detail";
    }

    @GetMapping("/search-hashtag")
    public String searchArticleHashtag(
            @RequestParam(required = false) String searchValue,
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
            Model model
    ) {

        Page<ArticleResponse> articles = articleService.searchArticlesViaHashtag(searchValue, pageable).map(ArticleResponse::from);
        List<String> hashtags = articleService.getHashtags();
        List<Integer> barNumbers = paginationService.getPaginationBara(pageable.getPageNumber(), articles.getTotalPages());

        model.addAttribute("seoAuthor", "By Daniel Park (Park jong chul)");
        model.addAttribute("seoGenerator", "By IntelliJ IDEA");
        model.addAttribute("seoDescript", "SEO  사이트 설명 입력할 곳");
        model.addAttribute("seoKeyword", "SEO 키워드 입력할 곳");

        model.addAttribute("title", "게시판 해시태그 목록");

        model.addAttribute("articles", articles);
        model.addAttribute("hashtags", hashtags);
        model.addAttribute("paginationBarNumbers", barNumbers);
        model.addAttribute("searchType", SearchType.HASHTAG);

        return "articles/search-hashtag";
    }

    @GetMapping("/form")
    public String articleForm(Model model) {

        model.addAttribute("seoAuthor", "By Daniel Park (Park jong chul)");
        model.addAttribute("seoGenerator", "By IntelliJ IDEA");
        model.addAttribute("seoDescript", "SEO  사이트 설명 입력할 곳");
        model.addAttribute("seoKeyword", "SEO 키워드 입력할 곳");

        model.addAttribute("title", "게시글");
        model.addAttribute("formStatus", FormStatus.CREATE);

        return "articles/form";
    }

    @PostMapping("/form")
    public String postNewArticle(
            @AuthenticationPrincipal SitePrincipal sitePrincipal,
            ArticleRequest articleRequest
    ) {

        articleService.saveArticle(articleRequest.toDto(sitePrincipal.toDto()));

        return "redirect:/articles";

    }


    @GetMapping("/{articleId}/form")
    public String updateArticleForm(
            @PathVariable Long articleId,
            Model model
    ) {
        ArticleResponse article = ArticleResponse.from(articleService.getArticle(articleId));

        model.addAttribute("seoAuthor", "By Daniel Park (Park jong chul)");
        model.addAttribute("seoGenerator", "By IntelliJ IDEA");
        model.addAttribute("seoDescript", "SEO  사이트 설명 입력할 곳");
        model.addAttribute("seoKeyword", "SEO 키워드 입력할 곳");

        model.addAttribute("title", "게시판 상세보기 : " + article.title());

        model.addAttribute("article", article);
        model.addAttribute("formStatus", FormStatus.UPDATE);


        return "articles/form";
    }

    @PostMapping("/{articleId}/form")
    public String updateArticle(
            @PathVariable Long articleId,
            @AuthenticationPrincipal SitePrincipal sitePrincipal,
            ArticleRequest articleRequest
    ) {

        articleService.updateArticle(articleId, articleRequest.toDto(sitePrincipal.toDto()));

        return "redirect:/articles/" + articleId;
    }

    @PostMapping("/{articleId}/delete")
    public String deleteArticle(
            @PathVariable Long articleId,
            @AuthenticationPrincipal SitePrincipal sitePrincipal
            ) {
        articleService.deleteArticle(articleId, sitePrincipal.getUsername());

        return "redirect:/articles";
    }
}
