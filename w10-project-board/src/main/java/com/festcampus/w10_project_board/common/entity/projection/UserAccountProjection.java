package com.festcampus.w10_project_board.common.entity.projection;

import com.festcampus.w10_project_board.common.entity.UserAccount;
import org.springframework.data.rest.core.config.Projection;

import java.time.LocalDateTime;

/**
 * packageName   : com.festcampus.w10_project_board.common.entity.projection
 * fileName     : UserAccountProjection
 * author       : danny
 * date         : 2024-06-05
 * description  :
 * ===========================================================
 * DATE             AUTHOR          NOTE
 * -----------------------------------------------------------
 * 2024-06-05          danny         최초 생성
 */
@Projection(name = "withoutPassword", types = UserAccount.class)
public interface UserAccountProjection {
    String getUserId();
    String getEmail();
    String getNickname();
    String getMemo();
    LocalDateTime getCreatedAt();
    String getCreatedBy();
    LocalDateTime getModifiedAt();
    String getModifiedBy();
}
