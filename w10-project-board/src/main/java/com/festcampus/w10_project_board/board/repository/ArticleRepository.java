package com.festcampus.w10_project_board.board.repository;

import com.festcampus.w10_project_board.common.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * packageName   : com.festcampus.w10_project_board.board.repository
 * fileName     : ArticleRepository
 * author       : danny
 * date         : 2024-06-01
 * description  :
 * ===========================================================
 * DATE             AUTHOR          NOTE
 * -----------------------------------------------------------
 * 2024-06-01          danny         최초 생성
 */
@RepositoryRestResource
public interface ArticleRepository extends JpaRepository<Article, Long> {

}
