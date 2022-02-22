package com.kazu.carp.specimen.creel.domain;

import com.kazu.carp.app.jpa.BaseEntity;
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
@Table(name = "creel_palette", schema = "specimen")
@Where(clause = "is_active=true")
@Getter @Setter @NoArgsConstructor @SuperBuilder
@Audited
public class CreelPalette extends BaseEntity {

    @Column(name = "version_number", nullable = false, columnDefinition = "SMALLINT default 1")
    private short versionNumber;

    @OneToOne(mappedBy = "creelPalette", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Creel creel;

    @OneToMany(mappedBy = "creelPalette", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private List<CreelPaletteLine> creelPaletteLines;
}
