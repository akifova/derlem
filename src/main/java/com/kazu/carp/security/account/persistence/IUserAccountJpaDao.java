package com.kazu.carp.security.account.persistence;

import com.kazu.carp.security.account.domain.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author akifova
 * @since 16.01.2021
 */
public interface IUserAccountJpaDao extends JpaRepository<UserAccount, String>, JpaSpecificationExecutor<UserAccount> {
    UserAccount findByEmail(String email);

    List<UserAccount> findAllByActive(boolean b);
}
