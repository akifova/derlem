package com.kazu.carp.yarn.definition.domain;

import com.kazu.carp.app.jpa.BaseEntity;
import com.kazu.carp.yarn.definition.dto.YarnPropertyType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.AttributeAccessor;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.List;

@Entity
@AttributeAccessor(value = "field")
@Table(name = "yarn_property", schema = "yarn", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})},
        indexes = {@Index(name = "ix_yarn_property_name", columnList = "name")})
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Audited
public class YarnProperty extends BaseEntity {

    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "yarn_property_type", nullable = true, length = 20)
    private YarnPropertyType yarnPropertyType;

    @Column(name = "print_on_barcode", nullable = false, columnDefinition = "SMALLINT default 0")
    private short printOnBarcode;

    @OneToMany(mappedBy = "yarnProperty", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<YarnGroupToProperty> yarnGroupToProperties;

    @OneToMany(mappedBy = "yarnProperty", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<YarnPropertyLine> yarnPropertyLines;
}
    
