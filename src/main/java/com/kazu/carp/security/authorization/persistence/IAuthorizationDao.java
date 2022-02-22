package com.kazu.carp.security.authorization.persistence;

import com.kazu.carp.security.authorization.domain.UserAuthorization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by sarparda on 08.08.2016.
 */
public interface IAuthorizationDao extends JpaRepository<UserAuthorization, String> {

    List<UserAuthorization> findByUserAccountEmail(String email);

    List<UserAuthorization> findByUserAccountId(String userAccountId);
}
