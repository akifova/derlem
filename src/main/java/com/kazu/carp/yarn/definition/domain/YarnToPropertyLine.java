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
@Table(name = "yarn_to_property_line", schema = "yarn", uniqueConstraints = {@UniqueConstraint(columnNames = {"yarn", "yarn_property_line"})},
        indexes = {@Index(name = "ix_yarn_to_property_line_yarn", columnList = "yarn")})
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Audited
public class YarnToPropertyLine extends BaseEntity {

    @JoinColumn(name = "yarn_property_line", referencedColumnName = "id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private YarnPropertyLine yarnPropertyLine;

    @JoinColumn(name = "yarn", referencedColumnName = "id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Yarn yarn;
}



