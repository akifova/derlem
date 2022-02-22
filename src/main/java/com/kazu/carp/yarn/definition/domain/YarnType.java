package com.kazu.carp.yarn.definition.domain;

import com.kazu.carp.app.jpa.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.AttributeAccessor;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


/**
 * @author akifova
 * 01.05.2021
 */
@Entity
@AttributeAccessor(value = "field")
@Table(name = "yarn_type", schema = "yarn", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})},
        indexes = {@Index(name = "ix_yarn_type_name", columnList = "name")})
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Audited
public class YarnType extends BaseEntity implements Serializable {
    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @OneToMany(mappedBy = "yarnType", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<YarnGroup> yarnGroups;
}
    
