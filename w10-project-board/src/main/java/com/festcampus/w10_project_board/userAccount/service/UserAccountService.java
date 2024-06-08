package com.festcampus.w10_project_board.userAccount.service;

import com.festcampus.w10_project_board.common.entity.UserAccount;
import com.festcampus.w10_project_board.userAccount.dto.UserAccountDto;
import com.festcampus.w10_project_board.userAccount.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * packageName   : com.festcampus.w10_project_board.userAccount.service
 * fileName     : UserAccountService
 * author       : danny
 * date         : 2024-06-08
 * description  :
 * ===========================================================
 * DATE             AUTHOR          NOTE
 * -----------------------------------------------------------
 * 2024-06-08          danny         최초 생성
 */
@RequiredArgsConstructor
@Transactional
@Service
public class UserAccountService {

    private final UserAccountRepository userAccountRepository;

    @Transactional(readOnly = true)
    public Optional<UserAccountDto> searchUser(String username) {
        return userAccountRepository.findById(username).map(UserAccountDto::from);
    }

    public UserAccountDto saveUser(String username, String password, String email, String nickname, String memo) {
        return UserAccountDto.from(userAccountRepository.save(UserAccount.of(username, password, email, nickname, memo)));
    }
}
