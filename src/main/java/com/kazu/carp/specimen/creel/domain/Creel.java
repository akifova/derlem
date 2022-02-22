package com.kazu.carp.specimen.creel.domain;

import com.kazu.carp.app.jpa.BaseEntity;
import com.kazu.carp.design.archive.domain.Archive;
import com.kazu.carp.design.plan.domain.DesignPlan;
import com.kazu.carp.specimen.creel.dto.Raising;
import com.kazu.carp.specimen.creel.dto.Washing;
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
@Table(name = "creel", schema = "specimen")
@Where(clause = "is_active=true")
@Getter @Setter @NoArgsConstructor @SuperBuilder
@Audited
public class Creel extends BaseEntity {

    @Column(name = "active_version", nullable = false, length = 10)
    private String activeVersion;

    @Column(name = "palette_code", nullable = false, length = 20)
    private String paletteCode;

    @Column(name = "quality_name", nullable = false, length = 20)
    private String qualityName;

    @Column(name = "sample_name", nullable = false, length = 50)
    private String sampleName;

    //Şardon
    @Enumerated(EnumType.STRING)
    @Column(name = "raising", nullable = true, length = 100)
    private Raising raising;

    //Yıkama
    @Column(name = "washing", nullable = true, length = 100)
    private Washing washing;

    @JoinColumn(name = "design_plan", referencedColumnName = "id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private DesignPlan designPlan;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "quality", referencedColumnName = "id")
    private CreelQuality creelQuality;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "creel_palette", referencedColumnName = "id")
    private CreelPalette creelPalette;

    @OneToMany(mappedBy = "creel", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private List<CreelToExplanation> explanations;

    @OneToMany(mappedBy = "creel", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Archive> archives;
}
