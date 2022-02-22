package com.kazu.carp.supplyChain.production.definition.stockCard.domain;

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
@Table(name = "quality", schema = "stock_card",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"code"}), @UniqueConstraint(columnNames = {"definition"})},
        indexes = {
                @Index(name = "ix_quality_definition", columnList = "definition"),
                @Index(name = "ix_quality_code", columnList = "code")
        })
@Getter
@Setter
@NoArgsConstructor
@Audited
@SuperBuilder
public class Quality extends BaseEntity {

    //Tarak Sayısı
    @Column(name = "crest", nullable = false, columnDefinition = "SMALLINT default 0")
    private int crest;

    //Atkı Sayısı
    @Column(name = "woof", nullable = false, columnDefinition = "SMALLINT default 0")
    private int woof;

    @Column(name = "code", nullable = false, length = 4)
    private String code;

    @Column(name = "definition", nullable = false, length = 100)
    private String definition;
}