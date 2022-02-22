package com.kazu.carp.specimen.creel.domain;

import com.kazu.carp.app.jpa.BaseEntity;
import com.kazu.carp.design.color.domain.Color;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.AttributeAccessor;
import org.hibernate.annotations.Where;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.List;

@Entity
@AttributeAccessor(value = "field")
@Table(name = "creel_palette_line", schema = "specimen")
@Where(clause = "is_active=true")
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Audited
public class CreelPaletteLine extends BaseEntity {

    @Column(name = "rotation_number", nullable = false, columnDefinition = "SMALLINT default 1")
    private int rotationNumber;

    @JoinColumn(name = "color", referencedColumnName = "id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Color color;

    @JoinColumn(name = "creel_palette", referencedColumnName = "id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private CreelPalette creelPalette;

    @OneToMany(mappedBy = "creelPaletteLine", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private List<CreelPaletteLineToYarn> lineToYarns;
}
