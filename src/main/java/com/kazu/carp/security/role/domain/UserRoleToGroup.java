package com.kazu.carp.security.role.domain;

import com.kazu.carp.app.jpa.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.AttributeAccessor;
import org.hibernate.envers.Audited;

import javax.persistence.*;

/**
 * Created by sarparda on 31.07.2016.
 */
@Entity
@AttributeAccessor(value = "field")
@Table(schema = "sec", name = "user_role_to_group", uniqueConstraints = {@UniqueConstraint(columnNames = {"role_group", "user_role"})},
        indexes = {@Index(name = "ix_role_to_group", columnList = "role_group")})
@Getter
@Setter
@NoArgsConstructor
@Audited
public class UserRoleToGroup extends BaseEntity {

    @JoinColumn(name = "user_role", referencedColumnName = "id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private UserRole role;

    @JoinColumn(name = "role_group", referencedColumnName = "id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private UserRoleGroup roleGroup;
}
    
