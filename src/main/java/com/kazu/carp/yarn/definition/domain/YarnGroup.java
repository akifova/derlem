package com.kazu.carp.yarn.definition.domain;

import com.kazu.carp.app.jpa.BaseEntity;
import com.kazu.carp.specimen.yarn.domain.SampleYarn;
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
@Table(name = "yarn_group", schema = "yarn", uniqueConstraints = {@UniqueConstraint(columnNames = {"yarn_type", "name"})},
        indexes = {@Index(name = "ix_yarn_group_name", columnList = "name"), @Index(name = "ix_yarn_group_type", columnList = "yarn_type")})
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Audited
public class YarnGroup extends BaseEntity implements Serializable {
    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @JoinColumn(name = "yarn_type", referencedColumnName = "id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private YarnType yarnType;

    @OneToMany(mappedBy = "yarnGroup", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private List<YarnGroupToProperty> properties;

    @OneToMany(mappedBy = "yarnGroup", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Yarn> yarns;

    @OneToMany(mappedBy = "yarnGroup", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<SampleYarn> sampleYarns;
}
    
