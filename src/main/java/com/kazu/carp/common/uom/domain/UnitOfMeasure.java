package com.kazu.carp.common.uom.domain;

import com.kazu.carp.app.jpa.BaseEntity;
import com.kazu.carp.yarn.definition.domain.Yarn;
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
@Table(name = "unit_of_measure", schema = "common", uniqueConstraints = {@UniqueConstraint(columnNames = {"unit"})},
        indexes = {@Index(name = "ix_unit_of_measure_unit", columnList = "unit")})
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Audited
public class UnitOfMeasure extends BaseEntity {

    @Column(name = "unit", nullable = false, length = 20)
    private String unit;

    @Column(name = "short_name", nullable = false, length = 10)
    private String shortName;

    @OneToMany(mappedBy = "unitOfMeasure", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Yarn> yarns;
}
    
