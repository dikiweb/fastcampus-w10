package com.festcampus.w10_project_board.board.controller;

import com.festcampus.w10_project_board.board.dto.request.ArticleCommentRequest;
import com.festcampus.w10_project_board.board.service.ArticleCommentService;
import com.festcampus.w10_project_board.userAccount.dto.security.SitePrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * packageName   : com.festcampus.w10_project_board.board.controller
 * fileName     : ArticleCommentController
 * author       : danny
 * date         : 2024-06-06
 * description  :
 * ===========================================================
 * DATE             AUTHOR          NOTE
 * -----------------------------------------------------------
 * 2024-06-06          danny         최초 생성
 */
@RequiredArgsConstructor
@RequestMapping("/comments")
@Controller
public class ArticleCommentController {

    private final ArticleCommentService articleCommentService;

    @PostMapping("/new")
    public String postNewArticleComment(
            @AuthenticationPrincipal SitePrincipal sitePrincipal,
            ArticleCommentRequest articleCommentRequest
    ) {

        articleCommentService.saveArticleComment(
                articleCommentRequest.toDto(sitePrincipal.toDto())
        );

        return "redirect:/articles/" + articleCommentRequest.articleId();
    }

    @PostMapping("/{commentId}/delete")
    public String deleteArticleComment(
            @PathVariable Long commentId,
            @AuthenticationPrincipal SitePrincipal sitePrincipal,
            Long articleId
    ) {
        articleCommentService.deleteArticleComment(commentId, sitePrincipal.getUsername());

        return "redirect:/articles/" + articleId;
    }
}
