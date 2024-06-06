package com.festcampus.w10_project_board.board.controller;

import com.festcampus.w10_project_board.board.dto.request.ArticleCommentRequest;
import com.festcampus.w10_project_board.board.dto.request.ArticleRequest;
import com.festcampus.w10_project_board.board.service.ArticleCommentService;
import com.festcampus.w10_project_board.userAccount.dto.UserAccountDto;
import lombok.RequiredArgsConstructor;
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
    public String postNewArticleComment(ArticleCommentRequest articleCommentRequest) {
        // TODO 인증정보를 넣어야 합니다
        articleCommentService.saveArticleComment(articleCommentRequest.toDto(UserAccountDto.of("diki", "1234", null, null, null)));

        return "redirect:/articles" + articleCommentRequest.articleId();
    }

    @PostMapping("/{commentId}/delete")
    public String deleteArticleComment(
            @PathVariable(name = "commentId") Long commentId,
            Long articleId
    ) {
        // TODO 인증정보를 넣어야 합니다
        articleCommentService.deleteArticleComment(commentId, "diki");
        return null;
    }
}
