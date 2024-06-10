package com.festcampus.w10_project_board.board.service;

import com.festcampus.w10_project_board.board.dto.ArticleCommentDto;
import com.festcampus.w10_project_board.board.repository.ArticleCommentRepository;
import com.festcampus.w10_project_board.board.repository.ArticleRepository;
import com.festcampus.w10_project_board.common.entity.Article;
import com.festcampus.w10_project_board.common.entity.ArticleComment;
import com.festcampus.w10_project_board.common.entity.UserAccount;
import com.festcampus.w10_project_board.userAccount.repository.UserAccountRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * packageName   : com.festcampus.w10_project_board.board.service
 * fileName     : ArticleCommentService
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
public class ArticleCommentService {

    private final ArticleRepository articleRepository;
    private final UserAccountRepository userAccountRepository;
    private final ArticleCommentRepository articleCommentRepository;

    /**
     * 댓글 검색
     */
    @Transactional(readOnly = true)
    public List<ArticleCommentDto> searchArticleComment(Long articleId) {
        return articleCommentRepository.findByArticle_Id(articleId).stream().map(ArticleCommentDto::from).toList();
    }

    public void saveArticleComment(ArticleCommentDto dto) {

        try {
            Article article = articleRepository.getReferenceById(dto.articleId());
            UserAccount userAccount = userAccountRepository.getReferenceById(dto.userAccountDto().userId());
            ArticleComment articleComment = dto.toEntity(article, userAccount);

            if (dto.parentCommentId() != null) {
                ArticleComment parentComment = articleCommentRepository.getReferenceById(dto.parentCommentId());
                parentComment.addChildComment(parentComment);
            } else {
                articleCommentRepository.save(articleComment);
            }

        } catch (EntityNotFoundException e) {
            log.warn("댓글 저장 실패. 댓글 작성에 필요한 정보를 찾을 수 없습니다 - {}", e.getLocalizedMessage());
        }

        Article article = articleRepository.getReferenceById(dto.articleId());
    }

    public void deleteArticleComment(Long articleCommentId, String userId) {
        articleCommentRepository.deleteByIdAndUserAccount_UserId(articleCommentId, userId);
    }
}
