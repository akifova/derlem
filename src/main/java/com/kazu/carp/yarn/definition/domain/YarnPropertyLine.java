package com.kazu.carp.yarn.definition.domain;

import com.kazu.carp.app.jpa.BaseEntity;
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
@Table(name = "yarn_property_line", schema = "yarn", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"yarn_property", "attribute"})},
        indexes = {@Index(name = "ix_yarn_property_line_yarn_property", columnList = "yarn_property")})
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Audited
public class YarnPropertyLine extends BaseEntity {

    @Column(name = "attribute", nullable = false, length = 40)
    private String attribute;

    @Column(name = "code", nullable = true, length = 10)
    private String code;

    @JoinColumn(name = "yarn_property", referencedColumnName = "id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private YarnProperty yarnProperty;

    @OneToMany(mappedBy = "yarnPropertyLine", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<YarnToPropertyLine> yarnToPropertyLines;
}
    
