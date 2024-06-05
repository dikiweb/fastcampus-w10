package com.festcampus.w10_project_board.board.repository;

import com.festcampus.w10_project_board.common.entity.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * packageName   : com.festcampus.w10_project_board.board.repository
 * fileName     : HashtagRepository
 * author       : danny
 * date         : 2024-06-05
 * description  :
 * ===========================================================
 * DATE             AUTHOR          NOTE
 * -----------------------------------------------------------
 * 2024-06-05          danny         최초 생성
 */
@RepositoryRestResource
public interface HashtagRepository extends
        JpaRepository<Hashtag, Long>,
        QuerydslPredicateExecutor<Hashtag>

{
    Optional<Hashtag> findByHashtagName(String hashtagName);

    List<Hashtag> findByHashtagNameIn(Set<String> hashtagNames);
}
