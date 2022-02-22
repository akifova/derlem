package com.kazu.carp.security.account.business;

import com.kazu.carp.security.account.domain.UserAccount;
import com.kazu.carp.security.account.dto.ChangePasswordDto;
import com.kazu.carp.security.account.dto.ResetPasswordDto;
import com.kazu.carp.security.account.dto.UserAccountDto;

import java.util.List;

/**
 * Created by sarparda on 31.07.2016.
 */
public interface IUserAccountService {
    UserAccount getById(String userAccountId);

    UserAccountDto findByEmail(String email);

    UserAccountDto loadUserByEmailForUserDetailService(String email);

    UserAccount getByEmail(String username);

    List<UserAccountDto> getUserList();

    UserAccountDto save(UserAccountDto userAccountDto);

    void update(UserAccountDto userAccountDto);

    void delete(String userId);

    UserAccount qryUserAccountById(String userId);

    void changePassword(ChangePasswordDto changePasswordDto);

    void resetPassword(ResetPasswordDto resetPasswordDto);

    List<UserAccountDto> search(String sp);
}
