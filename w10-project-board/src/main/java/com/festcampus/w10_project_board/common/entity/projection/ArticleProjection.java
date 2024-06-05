package com.festcampus.w10_project_board.common.entity.projection;

import com.festcampus.w10_project_board.common.entity.Article;
import com.festcampus.w10_project_board.common.entity.UserAccount;
import org.springframework.data.rest.core.config.Projection;

import java.time.LocalDateTime;

/**
 * packageName   : com.festcampus.w10_project_board.common.entity.projection
 * fileName     : ArticleProjection
 * author       : danny
 * date         : 2024-06-05
 * description  :
 * ===========================================================
 * DATE             AUTHOR          NOTE
 * -----------------------------------------------------------
 * 2024-06-05          danny         최초 생성
 */
@Projection(name = "withUserAccount", types = Article.class)
public interface ArticleProjection {
    Long getId();
    UserAccount getUserAccount();
    String getTitle();
    String getContent();
    LocalDateTime getCreatedAt();
    String getCreatedBy();
    LocalDateTime getModifiedAt();
    String getModifiedBy();
}
