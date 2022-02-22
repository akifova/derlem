package com.kazu.carp.supplyChain.production.definition.stockCard.domain;

import com.kazu.carp.app.jpa.BaseEntity;
import com.kazu.carp.supplyChain.production.definition.stockCard.dto.SizeType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.AttributeAccessor;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@AttributeAccessor(value = "field")
@Table(name = "size", schema = "stock_card",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"code"})
        },
        indexes = {@Index(name = "ix_measure_code", columnList = "code")}
)
@Getter
@Setter
@NoArgsConstructor
@Audited
public class Size extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "size_type", nullable = false, length = 20)
    private SizeType sizeType;

    @Column(name = "code", nullable = false, length = 4)
    private String code;

    @Column(name = "definition", nullable = false, length = 100)
    private String definition;

    @Column(name = "width", nullable = false, columnDefinition = "INT default 0")
    private int width;

    @Column(name = "length", nullable = false, columnDefinition = "INT default 0")
    private int length;

    @Column(name = "sqm", precision = 10, scale = 2, nullable = false, columnDefinition = "DECIMAL(10,2) default 0.00")
    private BigDecimal sqm;

    @Column(name = "inch", precision = 10, scale = 2, nullable = false, columnDefinition = "DECIMAL(10,2) default 0.00")
    private BigDecimal inch;
}
