package com.festcampus.w10_project_board.board.service;

import com.festcampus.w10_project_board.board.dto.ArticleDto;
import com.festcampus.w10_project_board.board.dto.ArticleUpdateDto;
import com.festcampus.w10_project_board.board.dto.ArticleWithCommentsDto;
import com.festcampus.w10_project_board.board.repository.ArticleRepository;
import com.festcampus.w10_project_board.common.entity.Article;
import com.festcampus.w10_project_board.common.entity.constant.SearchType;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * packageName   : com.festcampus.w10_project_board.board.service
 * fileName     : ArticleService
 * author       : danny
 * date         : 2024-06-03
 * description  :
 * ===========================================================
 * DATE             AUTHOR          NOTE
 * -----------------------------------------------------------
 * 2024-06-03          danny         최초 생성
 */
@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Transactional(readOnly = true)
    public Page<ArticleDto> searchArticls(SearchType searchType, String searchKeyword, Pageable pageable) {

        if (searchKeyword == null || searchKeyword.isBlank()) {
            return articleRepository.findAll(pageable).map(ArticleDto::from);
        }

        return switch (searchType) {
            case TITLE -> articleRepository.findByTitleContaining(searchKeyword, pageable).map(ArticleDto::from);
            case CONTENT -> articleRepository.findByContentContaining(searchKeyword, pageable).map(ArticleDto::from);
            case ID -> articleRepository.findByUserAccount_UserIdContaining(searchKeyword, pageable).map(ArticleDto::from);
            case NICKNAME -> articleRepository.findByUserAccount_NicknameContaining(searchKeyword, pageable).map(ArticleDto::from);
            case HASHTAG -> articleRepository
                    .findByHashtagNames(
                            Arrays.stream(searchKeyword.split(" ")).toList(), pageable
                    )
                    .map(ArticleDto::from);
        };
    }

    @Transactional(readOnly = true)
    public ArticleDto searchArticle(long articleId) {
        return null;
    }

    public long getArticleCount() {
        return articleRepository.count();
    }

    // TODO: HashtagService 로 이동을 고려해보자.
    public List<String> getHashtags() {
        return articleRepository.findAllDistinctHashtags();
    }

    @Transactional(readOnly = true)
    public Page<ArticleDto> searchArticlesViaHashtag(String hashtagName, Pageable pageable) {

        if (hashtagName == null || hashtagName.isBlank()) {
            return Page.empty(pageable);
        }

        return articleRepository.findByHashtagNames(List.of(hashtagName), pageable).map(ArticleDto::from);
    }

    @Transactional(readOnly = true)
    public ArticleWithCommentsDto getArticle(Long articleId) {
        return articleRepository
                .findById(articleId)
                .map(ArticleWithCommentsDto::from)
                .orElseThrow(() -> new EntityNotFoundException("게시글이 없습니다. - article: " + articleId));
    }


    // TODO : 인증과 연결해서 저장 하는것 다시 작업 해야합니다
    public void saveArticle(ArticleDto dto) {
//        articleRepository.save(dto.toEntity());
    }

    public void updateArticle(ArticleDto dto) {

        try {
            Article article = articleRepository.getReferenceById(dto.id());
            if (dto.title() != null) {
                article.setTitle(dto.title());
            }
            if (dto.content() != null) {
                article.setContent(dto.content());
            }
            // TODO: 아티클에 해시테그 부분을 넣어 주새요
        } catch (EntityNotFoundException e) {
            log.warn("게시글 없데이트 실패, 게시글을 찾을 수 없습니다. - dto: {}", dto);
        }

    }

    public void deleteArticle(long articleId) {
        articleRepository.deleteById(articleId);
    }
}
