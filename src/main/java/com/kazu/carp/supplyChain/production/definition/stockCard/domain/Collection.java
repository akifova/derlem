package com.kazu.carp.supplyChain.production.definition.stockCard.domain;

import com.kazu.carp.app.jpa.BaseEntity;
import com.kazu.carp.common.brand.domain.Brand;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.AttributeAccessor;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@AttributeAccessor(value = "field")
@Table(name = "collection", schema = "stock_card", uniqueConstraints = {@UniqueConstraint(columnNames = {"definition"}), @UniqueConstraint(columnNames = {"code"})},
        indexes = {@Index(name = "ix_collection_definition", columnList = "definition"), @Index(name = "ix_collection_code", columnList = "code")})
@Getter
@Setter
@NoArgsConstructor
@Audited
public class Collection extends BaseEntity {

    @Column(name = "code", nullable = false, length = 10)
    private String code;

    @Column(name = "definition", nullable = false, length = 100)
    private String definition;

    //Çekme Payı
    @Column(name = "shrinkage", nullable = false, columnDefinition = "SMALLINT default 0")
    private int shrinkage;

    @Column(name = "painted", nullable = false)
    private boolean painted;

    @Column(name = "horizontal_weaving", nullable = false)
    private boolean horizontalWeaving;

    @Column(name = "washed", nullable = false)
    private boolean washed;

    @JoinColumn(name = "brand", referencedColumnName = "id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Brand brand;
}
