package com.kazu.carp.supplyChain.production.definition.stockCard.domain;

import com.kazu.carp.app.jpa.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.AttributeAccessor;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@AttributeAccessor(value = "field")
@Table(name = "edge", schema = "stock_card", uniqueConstraints = {@UniqueConstraint(columnNames = {"code"})},
        indexes = {@Index(name = "ix_edge_code", columnList = "code")})

@Getter
@Setter
@NoArgsConstructor
@Audited
public class Edge extends BaseEntity {

    @Column(name = "definition", nullable = false, length = 100)
    private String definition;

    @Column(name = "code", nullable = false, length = 5)
    private String code;

    @Column(name = "length", nullable = false, columnDefinition = "SMALLINT default 0")
    private int length;
}
    
