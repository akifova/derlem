package com.kazu.carp.specimen.creel.domain;

import com.kazu.carp.app.jpa.BaseEntity;
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
@Table(name = "creel_quality",  schema = "specimen")
@Where(clause = "is_active=true")
@Getter @Setter @NoArgsConstructor @SuperBuilder
@Audited
public class CreelQuality extends BaseEntity {

    @Column(name = "version_number", nullable = false, columnDefinition = "SMALLINT default 1")
    private short versionNumber;

    //Atkı
    @Column(name = "woof", nullable = false, columnDefinition = "SMALLINT default 0")
    private int woof;

    //Tarak
    @Column(name = "crest", nullable = false, columnDefinition = "SMALLINT default 0")
    private int crest;

    //Lanset
    @Column(name = "lancet", nullable = false, columnDefinition = "SMALLINT default 0")
    private int lancet;

    //Atkı İpi (Alt)
    @JoinColumn(name = "bottom_woof_yarn", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Yarn bottomWoofYarn;

    //Atkı İpi (Orta)
    @JoinColumn(name = "middle_woof_yarn", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Yarn middleWoofYarn;

    //Atkı İpi (Üst)
    @JoinColumn(name = "top_woof_yarn", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Yarn topWoofYarn;

    //Örgü Yapısı
    @Column(name = "knitting_type", nullable = false, length = 20)
    private String knittingType;

    @OneToOne(mappedBy = "creelQuality", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Creel creel;
}
