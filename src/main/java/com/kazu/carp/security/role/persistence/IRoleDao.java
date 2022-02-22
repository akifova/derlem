package com.kazu.carp.security.role.persistence;

import com.kazu.carp.security.role.domain.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by sarparda on 03.08.2016.
 */
public interface IRoleDao extends JpaRepository<UserRole, String> {
}
