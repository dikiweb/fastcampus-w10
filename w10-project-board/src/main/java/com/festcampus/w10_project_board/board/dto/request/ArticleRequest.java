package com.festcampus.w10_project_board.board.dto.request;

import com.festcampus.w10_project_board.board.dto.ArticleDto;
import com.festcampus.w10_project_board.userAccount.dto.UserAccountDto;
import com.festcampus.w10_project_board.board.dto.HashtagDto;

import java.util.Set;

/**
 * packageName   : com.festcampus.w10_project_board.board.dto.request
 * fileName     : ArticleRequest
 * author       : danny
 * date         : 2024-06-04
 * description  :
 * ===========================================================
 * DATE             AUTHOR          NOTE
 * -----------------------------------------------------------
 * 2024-06-04          danny         최초 생성
 */
public record ArticleRequest(
        String title,
        String content
) {

    public static ArticleRequest of(String title, String content) {
        return new ArticleRequest(title, content);
    }

    public ArticleDto toDto(UserAccountDto userAccountDto) {
        return toDto(userAccountDto, null);
    }

    public ArticleDto toDto(UserAccountDto userAccountDto, Set<HashtagDto> hashtagDtos) {
        return ArticleDto.of(
                userAccountDto,
                title,
                content,
                hashtagDtos
        );
    }

}
