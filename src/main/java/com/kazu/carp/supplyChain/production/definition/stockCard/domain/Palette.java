package com.kazu.carp.supplyChain.production.definition.stockCard.domain;

import com.kazu.carp.app.jpa.BaseEntity;
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
@Table(name = "palette", schema = "stock_card", uniqueConstraints = {@UniqueConstraint(columnNames = {"code"})},
    indexes = {@Index(name = "ix_palette_code", columnList = "code")})
@Getter @Setter @NoArgsConstructor @SuperBuilder
@Audited
public class Palette extends BaseEntity {

    @Column(name = "code", nullable = false, length = 3)
    private String code;

    @Column(name = "definition", nullable = false, length = 100)
    private String definition;

    @OneToMany(mappedBy = "palette", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private List<PaletteLine> paletteLines;
}
