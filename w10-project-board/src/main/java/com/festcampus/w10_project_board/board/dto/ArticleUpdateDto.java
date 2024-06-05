package com.festcampus.w10_project_board.board.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * packageName   : com.festcampus.w10_project_board.board.dto
 * fileName     : ArticleDto
 * author       : danny
 * date         : 2024-06-03
 * description  :
 * ===========================================================
 * DATE             AUTHOR          NOTE
 * -----------------------------------------------------------
 * 2024-06-03          danny         최초 생성
 */
@Getter
@Setter
public class ArticleUpdateDto {

    private String title;
    private String content;
    private String hashtag;


    protected ArticleUpdateDto() {
    }

    private ArticleUpdateDto(String title, String content, String hashtag) {
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
    }

    public static ArticleUpdateDto of(String title, String content, String hashtag) {
        return new ArticleUpdateDto(title, content, hashtag);
    }
}
