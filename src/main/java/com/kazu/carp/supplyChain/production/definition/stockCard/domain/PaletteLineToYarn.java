package com.kazu.carp.supplyChain.production.definition.stockCard.domain;

import com.kazu.carp.app.jpa.BaseEntity;
import com.kazu.carp.yarn.definition.domain.Yarn;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.AttributeAccessor;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;


/**
 * @author akifova
 * 08.02.2021
 */
@Entity
@AttributeAccessor(value = "field")
@Table(name = "palette_line_to_yarn", schema = "stock_card",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"palette_line", "yarn"})},
        indexes = {@Index(name = "ix_palette_line_to_yarn_palette_line", columnList = "palette_line")})
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Audited
public class PaletteLineToYarn extends BaseEntity implements Serializable {

    @Column(name = "latte_percentage", nullable = false, columnDefinition = "SMALLINT default 0")
    private short lattePercentage;

    @JoinColumn(name = "palette_line", referencedColumnName = "id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private PaletteLine paletteLine;

    @JoinColumn(name = "yarn", referencedColumnName = "id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Yarn yarn;
}
    
