package com.kazu.carp.specimen.creel.domain;

import com.kazu.carp.app.jpa.BaseEntity;
import com.kazu.carp.specimen.yarn.domain.SampleYarn;
import com.kazu.carp.yarn.definition.domain.Yarn;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.AttributeAccessor;
import org.hibernate.annotations.Where;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@AttributeAccessor(value = "field")
@Table(name = "creel_palette_line_to_yarn", schema = "specimen")
@Where(clause = "is_active=true")
@Getter @Setter @NoArgsConstructor @SuperBuilder
@Audited
public class CreelPaletteLineToYarn extends BaseEntity {

    @Column(name = "latte_percentage", nullable = false, columnDefinition = "SMALLINT default 0")
    private short lattePercentage;

    @JoinColumn(name = "creel_palette_line", referencedColumnName = "id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private CreelPaletteLine creelPaletteLine;

    @JoinColumn(name = "yarn", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Yarn yarn;

    @JoinColumn(name = "sample_yarn", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private SampleYarn sampleYarn;
}
