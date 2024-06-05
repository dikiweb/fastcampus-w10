package com.festcampus.w10_project_board.board.service;

import com.festcampus.w10_project_board.board.dto.ArticleDto;
import com.festcampus.w10_project_board.board.dto.ArticleUpdateDto;
import com.festcampus.w10_project_board.board.repository.ArticleRepository;
import com.festcampus.w10_project_board.common.config.SecurityConfig;
import com.festcampus.w10_project_board.common.entity.Article;
import com.festcampus.w10_project_board.common.entity.constant.SearchType;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

/**
 * packageName   : com.festcampus.w10_project_board.board.service
 * fileName     : ArticleServiceTest
 * author       : danny
 * date         : 2024-06-03
 * description  :
 * ===========================================================
 * DATE             AUTHOR          NOTE
 * -----------------------------------------------------------
 * 2024-06-03          danny         최초 생성
 */
@DisplayName("비지니스(기능) 로직 - 게시글")
@Import(SecurityConfig.class)
@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {

    @InjectMocks
    private ArticleService sut;

    @Mock
    private ArticleRepository articleRepository;


    @DisplayName("게시글을 검색하면 게시글 리스트를 반환 / 페이지네이션 / 정렬 포함")
    @Test
    void givenSearchParameter_whenSercingArticles_thenReturnArticlesList() {
        // given

        // when
        //Page<ArticleDto> articles = sut.searchArticls(SearchType.TITLE, "search keyword");

        // then
        //assertThat(articles).isNotNull();
    }

    @Disabled("구현 중")
    @DisplayName("게시글을 조회하면 게시글을 반환")
    @Test
    void givenArticleId_whenSercingArticle_thenReturnArticle() {
        // given

        // when
        //ArticleDto article = sut.searchArticl(1L);

        // then
        //assertThat(article).isNotNull();
    }

    @Disabled("구현 중")
    @DisplayName("게시글 정보를 입력하면 게시글을 생성한다")
    @Test
    void givenArticleInfo_whenSavingArticle_thenSaveArticle() {
        // given
        //ArticleDto dto = ArticleDto.of("제목", "내용", "#해시", LocalDateTime.now(), "diki");
        //given(articleRepository.save(any(Article.class))).willReturn(null);

        // when
        //sut.saveArticle(dto);

        // then
        //then(articleRepository).should().save(any(Article.class));
    }

    @Disabled("구현 중")
    @DisplayName("게시글의 ID와 수정정보를 입력하면 게시글을 수정한다")
    @Test
    void givenArticleIdAndIModifiedInfo_whenSavingArticle_thenUpdateArticle() {
        // given
        // ArticleUpdateDto dto = ArticleUpdateDto.of("제목", "내용", "#해시");
        // given(articleRepository.save(any(Article.class))).willReturn(null);

        // when
        // sut.updateArticle(1L, dto);

        // then
        // then(articleRepository).should().save(any(Article.class));
    }

    @Disabled("구현 중")
    @DisplayName("게시글의 ID를 입력하면 게시글을 삭제한다")
    @Test
    void givenArticleId_whenDeletingArticle_thenDeleteArticle() {
        // given
        willDoNothing().given(articleRepository).delete(any(Article.class));

        // when
        sut.deleteArticle(1L);

        // then
        then(articleRepository).should().delete(any(Article.class));
    }
}