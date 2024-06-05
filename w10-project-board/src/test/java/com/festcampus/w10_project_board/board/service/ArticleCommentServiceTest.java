package com.festcampus.w10_project_board.board.service;

import com.festcampus.w10_project_board.board.dto.ArticleCommentDto;
import com.festcampus.w10_project_board.board.repository.ArticleCommentRepository;
import com.festcampus.w10_project_board.board.repository.ArticleRepository;
import com.festcampus.w10_project_board.common.config.SecurityConfig;
import com.festcampus.w10_project_board.common.entity.Article;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

/**
 * packageName   : com.festcampus.w10_project_board.board.service
 * fileName     : ArticleCommentServiceTest
 * author       : danny
 * date         : 2024-06-03
 * description  :
 * ===========================================================
 * DATE             AUTHOR          NOTE
 * -----------------------------------------------------------
 * 2024-06-03          danny         최초 생성
 */
@DisplayName("비지니스(기능) 로직 - 댓글")
@Import(SecurityConfig.class)
@ExtendWith(MockitoExtension.class)
class ArticleCommentServiceTest {

    @InjectMocks
    private ArticleCommentService sut;

    @Mock
    private ArticleRepository articleRepository;
    @Mock
    private ArticleCommentRepository articleCommentRepository;


    @Disabled("구현 중")
    @DisplayName("게시글 ID로 조회하면 홰당하는 댓글 목록을 반환한다")
    @Test
    void givenArticleId_whenSearchingComment_thenReturnsComments() {
        // given
        Long articleId = 1L;
        given(articleRepository.findById(articleId)).willReturn(Optional.of(
                Article.of("title", "content", "#hash")
        ));

        // when
        List<ArticleCommentDto> articleComments = sut.searchArticleComment(articleId);

        // then
        assertThat(articleComments).isNotNull();
        then(articleRepository).should().findById(articleId);
    }
}