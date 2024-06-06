package com.festcampus.w10_project_board.board.repository.querydsl;

import java.util.List;

/**
 * packageName   : com.festcampus.w10_project_board.board.repository.querydsl
 * fileName     : HashtagRepositoryCustom
 * author       : danny
 * date         : 2024-06-06
 * description  :
 * ===========================================================
 * DATE             AUTHOR          NOTE
 * -----------------------------------------------------------
 * 2024-06-06          danny         최초 생성
 */
public interface HashtagRepositoryCustom {
    List<String> findAllHashtagNames();
}
