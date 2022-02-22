package com.kazu.carp.yarn.definition.domain;

import com.kazu.carp.app.jpa.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.AttributeAccessor;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@AttributeAccessor(value = "field")
@Table(name = "yarn_group_to_property", schema = "yarn", uniqueConstraints = {@UniqueConstraint(columnNames = {"yarn_property", "yarn_group"})},
        indexes = {@Index(name = "ix_yarn_group_to_property_yarn_group", columnList = "yarn_group")})
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Audited
public class YarnGroupToProperty extends BaseEntity {

    @JoinColumn(name = "yarn_property", referencedColumnName = "id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private YarnProperty yarnProperty;

    @JoinColumn(name = "yarn_group", referencedColumnName = "id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private YarnGroup yarnGroup;
}
    
