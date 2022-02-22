package com.kazu.carp.supplyChain.production.definition.stockCard.domain;

import com.kazu.carp.app.jpa.BaseEntity;
import com.kazu.carp.design.color.domain.Color;
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
@Table(name = "palette_line", schema = "stock_card", uniqueConstraints = {@UniqueConstraint(columnNames = {"palette", "rotation_number"})},
        indexes = {@Index(name = "ix_palette_line_palette", columnList = "palette")})
@Getter @Setter @NoArgsConstructor @SuperBuilder
@Audited
public class PaletteLine extends BaseEntity {

    @Column(name = "rotation_number", nullable = false, columnDefinition = "SMALLINT default 1")
    private int rotationNumber;

    @JoinColumn(name = "color", referencedColumnName = "id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Color color;

    @JoinColumn(name = "palette", referencedColumnName = "id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Palette palette;

    @OneToMany(mappedBy = "paletteLine", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private List<PaletteLineToYarn> lineToYarns;
}
