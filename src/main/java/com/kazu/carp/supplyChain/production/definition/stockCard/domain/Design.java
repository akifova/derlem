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
@Table(name = "design", schema = "stock_card",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"code"})},
        indexes = {@Index(name = "ix_design_code", columnList = "code")})
@Getter
@Setter
@NoArgsConstructor
@Audited
public class Design extends BaseEntity {

    @Column(name = "code", nullable = false, length = 5)
    private String code;
}
