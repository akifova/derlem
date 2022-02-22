package com.kazu.carp.security.role.domain;

import com.kazu.carp.app.jpa.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.AttributeAccessor;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.List;

/**
 * Created by sarparda on 31.07.2016.
 */
@Entity
@AttributeAccessor(value = "field")
@Table(schema = "sec", name = "user_role_group", uniqueConstraints = { @UniqueConstraint(columnNames = { "name"}) },
        indexes = { @Index(name = "ix_role_group_name", columnList = "name")})
@Getter
@Setter
@NoArgsConstructor
@Audited
public class UserRoleGroup extends BaseEntity {

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @OneToMany(mappedBy = "roleGroup", fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<UserRoleToGroup> roleToGroups;
}
