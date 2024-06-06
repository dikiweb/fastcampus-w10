package com.festcampus.w10_project_board.board.service;

import com.festcampus.w10_project_board.board.dto.ArticleDto;
import com.festcampus.w10_project_board.board.dto.ArticleUpdateDto;
import com.festcampus.w10_project_board.board.dto.ArticleWithCommentsDto;
import com.festcampus.w10_project_board.board.repository.ArticleRepository;
import com.festcampus.w10_project_board.common.entity.Article;
import com.festcampus.w10_project_board.common.entity.Hashtag;
import com.festcampus.w10_project_board.common.entity.UserAccount;
import com.festcampus.w10_project_board.common.entity.constant.SearchType;
import com.festcampus.w10_project_board.userAccount.repository.UserAccountRepository;
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
import java.util.Set;
import java.util.stream.Collectors;

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
    private final UserAccountRepository userAccountRepository;
    private final HashtagService hashtagService;

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

    public void updateArticle(Long articleId, ArticleDto dto) {

        try {
            Article article = articleRepository.getReferenceById(articleId);
            UserAccount userAccount = userAccountRepository.getReferenceById(dto.userAccountDto().userId());

            if (article.getUserAccount().equals(userAccount)) {

                if (dto.title() != null) {
                    article.setTitle(dto.title());
                }
                if (dto.content() != null) {
                    article.setContent(dto.content());
                }

                Set<Long> hashtagIds = article.getHashtags().stream().map(Hashtag::getId).collect(Collectors.toUnmodifiableSet());
                article.clearHashtags();
                articleRepository.flush();

                hashtagIds.forEach(hashtagService::deleteHashtagWithoutArticles);

                Set<Hashtag> hashtags = renewHashtagsFromContent(dto.content());
                article.addHashtags(hashtags);
            }

        } catch (EntityNotFoundException e) {
            log.warn("게시글 없데이트 실패, 업데이트에 필요한 정보를 찾을 수 없습니다. - {}", e.getLocalizedMessage());
        }

    }

    public void deleteArticle(long articleId, String userId) {
        articleRepository.deleteByIdAndUserAccount_UserId(articleId, userId);
    }

    private Set<Hashtag> renewHashtagsFromContent(String content) {
        Set<String> hashtagNamesInContent = hashtagService.parseHashtagNames(content);
        Set<Hashtag> hashtags = hashtagService.findHashtagsByNames(hashtagNamesInContent);
        Set<String> existingHashtagNames = hashtags.stream().map(Hashtag::getHashtagName).collect(Collectors.toUnmodifiableSet());

        hashtagNamesInContent.forEach(newHashtagName -> {
            if (!existingHashtagNames.contains(newHashtagName)) {
                hashtags.add(Hashtag.of(newHashtagName));
            }
        });
        return hashtags;
    }
}
