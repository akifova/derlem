package com.kazu.carp.security.authorization.domain;

import com.kazu.carp.app.jpa.BaseEntity;
import com.kazu.carp.security.account.domain.UserAccount;
import com.kazu.carp.security.role.domain.UserRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.AttributeAccessor;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by sarparda on 31.07.2016.
 */

@Entity
@AttributeAccessor(value = "field")
@Table(schema = "sec", name = "user_authorization", uniqueConstraints = { @UniqueConstraint(columnNames = { "user_account", "user_role"}) })
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Audited
public class UserAuthorization extends BaseEntity implements Serializable {

    @JoinColumn(name = "user_account", referencedColumnName = "id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private UserAccount userAccount;

    @JoinColumn(name = "user_role", referencedColumnName = "id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private UserRole role;
}
