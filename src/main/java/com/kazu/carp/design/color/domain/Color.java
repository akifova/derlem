package com.kazu.carp.design.color.domain;

import com.kazu.carp.app.jpa.BaseEntity;
import com.kazu.carp.specimen.creel.domain.CreelPaletteLine;
import com.kazu.carp.supplyChain.production.definition.stockCard.domain.PaletteLine;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.AttributeAccessor;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@AttributeAccessor(value = "field")
@Table(name = "color", schema = "design",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"rgb_red", "rgb_green", "rgb_blue"})},
        indexes = {@Index(name = "ix_color_code", columnList = "code"),
                @Index(name = "ix_color_rgb", columnList = "rgb_red, rgb_green, rgb_blue")})
@Getter
@Setter
@NoArgsConstructor
@Audited
@SuperBuilder
public class Color extends BaseEntity implements Serializable {

    @Column(name = "code", nullable = false, length = 12)
    private String code;

    @Column(name = "definition", nullable = false, length = 100)
    private String definition;

    @Column(name = "rgb_red", nullable = false, columnDefinition = "INT default 0")
    private Integer rgbRed;

    @Column(name = "rgb_green", nullable = false, columnDefinition = "INT default 0")
    private Integer rgbGreen;

    @Column(name = "rgb_blue", nullable = false, columnDefinition = "INT default 0")
    private Integer rgbBlue;

    @OneToMany(mappedBy = "color", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<PaletteLine> lines;

    @OneToMany(mappedBy = "color", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<CreelPaletteLine> creelPaletteLines;
}
    
