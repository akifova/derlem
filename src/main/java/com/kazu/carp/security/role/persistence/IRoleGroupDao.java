package com.kazu.carp.security.role.persistence;
import com.kazu.carp.security.role.domain.UserRoleGroup;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Mehmet Akif Ova on 3.08.2017.
 */
public interface IRoleGroupDao extends JpaRepository<UserRoleGroup, String> {
}
