package com.kazu.carp.security.account.business;

import com.kazu.carp.app.jpa.specification.SpecCreator;
import com.kazu.carp.common.util.business.IComplementaryService;
import com.kazu.carp.security.account.domain.UserAccount;
import com.kazu.carp.security.account.dto.ChangePasswordDto;
import com.kazu.carp.security.account.dto.IUserAccountMapper;
import com.kazu.carp.security.account.dto.ResetPasswordDto;
import com.kazu.carp.security.account.dto.UserAccountDto;
import com.kazu.carp.security.account.exception.PasswordIsNotCorrectException;
import com.kazu.carp.security.account.exception.PasswordsNotMatchedException;
import com.kazu.carp.security.account.persistence.IUserAccountJpaDao;
import com.kazu.carp.security.authorization.domain.UserAuthorization;
import com.kazu.carp.security.role.business.IRoleService;
import com.kazu.carp.security.role.domain.UserRole;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author akifova
 * @since 16.01.2021
 */
@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackForClassName = { "Exception" })
public class UserAccountServiceImpl implements IUserAccountService {

    final
    IUserAccountJpaDao dao;

    final
    IRoleService roleService;


    final PasswordEncoder passwordEncoder;

    final IUserAccountMapper userAccountMapper;

    final IComplementaryService complementaryService;

    public UserAccountServiceImpl(IUserAccountJpaDao dao, IRoleService roleService, PasswordEncoder passwordEncoder,
                                  IUserAccountMapper userAccountMapper, IComplementaryService complementaryService) {
        this.dao = dao;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.userAccountMapper = userAccountMapper;
        this.complementaryService = complementaryService;
    }

    @Override
    public UserAccount getById(String userAccountId) {
        return dao.getById(userAccountId);
    }

    @Override
    public UserAccountDto findByEmail(String email) {
        UserAccount userAccount = dao.findByEmail(email);
        UserAccountDto dto = userAccountMapper.toDto(userAccount);
        return dto;
    }

    @Override
    public UserAccountDto loadUserByEmailForUserDetailService(String email) {
        UserAccount userAccount = dao.findByEmail(email);
        UserAccountDto dto = userAccountMapper.toDto(userAccount);
        dto.setPassword(userAccount.getPassword());
        return dto;
    }

    @Override
    public UserAccount getByEmail(String email) {
        return dao.findByEmail(email);
    }

    @Override
    public List<UserAccountDto> getUserList() {
        List<UserAccount> userAccounts = dao.findAllByActive(true);
        List<UserAccountDto> userAccountDtos = new ArrayList<>();

        userAccounts.stream().forEach(userAccount -> {
            UserAccountDto dto = userAccountMapper.toDto(userAccount);
            dto.setPassword(null);
            userAccountDtos.add(dto);
        });
        return userAccountDtos;
    }

    @Override
    public UserAccountDto save(UserAccountDto userAccountDto) {
        UserAccount userAccount = new UserAccount();
        userAccount.setEmail(userAccountDto.getEmail());
        userAccount.setPassword(passwordEncoder.encode(userAccountDto.getPassword()));
        userAccount.setName(userAccountDto.getName());
        userAccount.setSurname(userAccountDto.getSurname());
        List<UserAuthorization> userAuthorizations = new ArrayList<>();

        userAccountDto.getAuthorizations().stream()
                .filter(f->f.isActive())
                .forEach(a->{
            UserRole userRole = roleService.getById(a.getRole().getId());
            UserAuthorization userAuthorization = new UserAuthorization();
            userAuthorization.setUserAccount(userAccount);
            userAuthorization.setRole(userRole);
            userAuthorization.setActive(a.isActive());
            userAuthorizations.add(userAuthorization);
        });

        userAccount.setAuthorizations(userAuthorizations);
        dao.save(userAccount);
        UserAccountDto dto = userAccountMapper.toDto(userAccount);
        dto.setPassword(null);
        return dto;
    }

    @Override
    public void update(UserAccountDto userAccountDto) {
        UserAccount userAccount = qryUserAccountById(userAccountDto.getId());
        userAccount.setEmail(userAccountDto.getEmail());
        userAccount.setName(userAccountDto.getName());
        userAccount.setSurname(userAccountDto.getSurname());
        dao.save(userAccount);
    }

    @Override
    public void delete(String userId) {
        UserAccount userAccount = qryUserAccountById(userId);
        userAccount.setEmail(String.format("%s@deleted.usr", complementaryService.createUniqueFromMillis()));
        userAccount.setActive(false);
        userAccount.getAuthorizations().stream().forEach(a->{
            a.setActive(false);
        });

        userAccount.setPassword("deletedUSer");
        dao.save(userAccount);
    }

    @Override
    public UserAccount qryUserAccountById(String userId) {
        return dao.getById(userId);
    }

    @Override
    public void changePassword(ChangePasswordDto changePasswordDto) {
        UserAccount userAccount = qryUserAccountById(changePasswordDto.getUserId());

        try {
            if(!passwordEncoder.matches(changePasswordDto.getOldPassword(), userAccount.getPassword())) {
                throw new PasswordIsNotCorrectException();
            }

            if(!changePasswordDto.getConfirmPassword().equals(changePasswordDto.getNewPassword())) {
                throw new PasswordsNotMatchedException();
            }
        }
        catch (PasswordsNotMatchedException pnmEx) {
            pnmEx.printStackTrace();
        } catch (PasswordIsNotCorrectException e) {
            e.printStackTrace();
        }

        userAccount.setPassword(passwordEncoder.encode(changePasswordDto.getNewPassword()));
        dao.save(userAccount);
    }

    @Override
    public void resetPassword(ResetPasswordDto resetPasswordDto) {
        UserAccount userAccount = qryUserAccountById(resetPasswordDto.getUserId());
        userAccount.setPassword(passwordEncoder.encode(resetPasswordDto.getNewPassword()));
        dao.save(userAccount);
    }

    @Override
    public List<UserAccountDto> search(String sp) {
        var spec = new SpecCreator<UserAccount>(sp).getSpec();
        return dao.findAll(spec).stream().map(userAccountMapper::toDto).collect(Collectors.toList());
    }
}
