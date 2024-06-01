package com.festcampus.w10_project_board.common.entity;

import java.time.LocalDateTime;

/**
 * packageName   : com.festcampus.w10_project_board.common.entity
 * fileName     : ArticleComment
 * author       : danny
 * date         : 2024-06-01
 * description  :
 * ===========================================================
 * DATE             AUTHOR          NOTE
 * -----------------------------------------------------------
 * 2024-06-01          danny         최초 생성
 */
public class ArticleComment {

    private Long id;
    private Article article; // 게시글 아이디
    private String content; // 댓글 내용

    private LocalDateTime createdAt;  // 자성일시
    private String createdBy;  // 작성자
    private LocalDateTime modifiedAt; // 수정일시
    private String modifiedBy;  // 수정자
}
