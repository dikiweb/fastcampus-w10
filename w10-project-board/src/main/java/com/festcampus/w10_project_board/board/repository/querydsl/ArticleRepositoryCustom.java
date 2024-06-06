package com.festcampus.w10_project_board.board.repository.querydsl;

import com.festcampus.w10_project_board.common.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

/**
 * packageName   : com.festcampus.w10_project_board.board.repository.querydsl
 * fileName     : ArticleRepositoryCustom
 * author       : danny
 * date         : 2024-06-06
 * description  :
 * ===========================================================
 * DATE             AUTHOR          NOTE
 * -----------------------------------------------------------
 * 2024-06-06          danny         최초 생성
 */
public interface ArticleRepositoryCustom {

    @Deprecated
    List<String> findAllDistinctHashtags();
    Page<Article> findByHashtagNames(Collection<String> hashtagNames, Pageable pageable);
}
