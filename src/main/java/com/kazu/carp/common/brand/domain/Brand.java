package com.kazu.carp.common.brand.domain;

import com.kazu.carp.app.jpa.BaseEntity;
import com.kazu.carp.supplyChain.production.definition.stockCard.domain.Collection;
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
@Table(schema = "common", name = "brand",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name"}),
            @UniqueConstraint(columnNames = {"code"})
    },
    indexes = {
        @Index(name = "ix_brand_name", columnList = "name")
    })
@Getter
@Setter
@NoArgsConstructor
@Audited
@SuperBuilder
public class Brand extends BaseEntity {

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "code", nullable = false, length = 3)
    private String code;

    @OneToMany(mappedBy = "brand", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Collection> collections;
}
