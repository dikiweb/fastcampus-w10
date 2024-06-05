package com.festcampus.w10_project_board.userAccount.repository;

import com.festcampus.w10_project_board.common.entity.UserAccount;
import com.festcampus.w10_project_board.common.entity.projection.UserAccountProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * packageName   : com.festcampus.w10_project_board.userAccount
 * fileName     : UserAccountRepository
 * author       : danny
 * date         : 2024-06-04
 * description  :
 * ===========================================================
 * DATE             AUTHOR          NOTE
 * -----------------------------------------------------------
 * 2024-06-04          danny         최초 생성
 */
@RepositoryRestResource(excerptProjection = UserAccountProjection.class)
public interface UserAccountRepository extends JpaRepository<UserAccount, String> {
}
