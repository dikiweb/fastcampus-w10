package com.festcampus.w10_project_board.board.dto.request;

import com.festcampus.w10_project_board.board.dto.ArticleCommentDto;
import com.festcampus.w10_project_board.userAccount.dto.UserAccountDto;

/**
 * packageName   : com.festcampus.w10_project_board.board.dto.request
 * fileName     : ArticleCommentRequest
 * author       : danny
 * date         : 2024-06-04
 * description  :
 * ===========================================================
 * DATE             AUTHOR          NOTE
 * -----------------------------------------------------------
 * 2024-06-04          danny         최초 생성
 */
public record ArticleCommentRequest(
        Long articleId,
        Long parentCommentId,
        String content
) {
    public static ArticleCommentRequest of(Long articleId, String content) {
        return ArticleCommentRequest.of(articleId, null, content);
    }

    public static ArticleCommentRequest of(Long articleId, Long parentCommentId, String content) {
        return new ArticleCommentRequest(articleId, parentCommentId, content);
    }

    public ArticleCommentDto toDto(UserAccountDto userAccountDto) {
        return ArticleCommentDto.of(
                articleId,
                userAccountDto,
                parentCommentId,
                content
        );
    }
}
