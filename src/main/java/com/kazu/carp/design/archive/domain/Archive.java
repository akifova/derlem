package com.kazu.carp.design.archive.domain;

import com.kazu.carp.app.jpa.BaseEntity;
import com.kazu.carp.common.firm.domain.Firm;
import com.kazu.carp.design.archive.dto.ArchiveStatus;
import com.kazu.carp.design.archive.dto.WeaveType;
import com.kazu.carp.design.plan.domain.DesignPlan;
import com.kazu.carp.security.account.domain.UserAccount;
import com.kazu.carp.specimen.creel.domain.Creel;
import com.kazu.carp.supplyChain.production.definition.stockCard.domain.*;
import com.kazu.carp.yarn.definition.domain.Yarn;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.AttributeAccessor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;


/**
 * @author akifova
 * 07.06.2021
 */
@Entity
@AttributeAccessor(value = "field")
@Table(name = "archive", schema = "design",
        indexes = {@Index(name = "ix_archive_design_code", columnList = "design_code")})
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Audited
public class Archive extends BaseEntity {
    @Column(name = "design_code", nullable = false, length = 30)
    private String designName;

    @Column(name = "star_rating", columnDefinition = "SMALLINT default 0")
    private short starRating;

    @Column(name = "difficulty")
    private Integer difficulty;

    @Column(name = "version_number")
    private short versionNumber;

    @Column(name = "version_date")
    private LocalDateTime versionDate;

    @Column(name = "tag", length = 400)
    private String tag;

    @Enumerated(EnumType.STRING)
    @Column(name = "archive_status", nullable = false, length = 20)
    private ArchiveStatus archiveStatus;

    @JoinColumn(name = "creel", referencedColumnName = "id", nullable = true)
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Creel creel;

    //Koleksiyon
    @JoinColumn(name = "collection", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Collection collection;

    //Kalite Kodu
    @JoinColumn(name = "quality", referencedColumnName = "id", nullable = true)
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Quality quality;

    //Palet Kodu
    @JoinColumn(name = "palette", referencedColumnName = "id", nullable = true)
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Palette palette;

    //Bordür Rengi
    @JoinColumn(name = "bordure", referencedColumnName = "id", nullable = true)
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private PaletteLineToYarn bordure;

    //Zemin Rengi
    @JoinColumn(name = "floor", referencedColumnName = "id", nullable = true)
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private PaletteLineToYarn floor;

    //Desen Kodu
    @JoinColumn(name = "design", referencedColumnName = "id", nullable = true)
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Design design;

    //Ebat
    @JoinColumn(name = "size", referencedColumnName = "id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Size size;

    //Şekil
    @JoinColumn(name = "shape", referencedColumnName = "id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Shape shape;

    //Kenar Formu
    @JoinColumn(name = "edge", referencedColumnName = "id", nullable = true)
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Edge edge;

    @JoinColumn(name = "overlock_yarn", referencedColumnName = "id", nullable = true)
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Yarn overlockYarn;

    @JoinColumn(name = "side_edge", referencedColumnName = "id", nullable = true)
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Edge sideEdge;

    //Saçak
    /*@JoinColumn(name = "fringe", referencedColumnName = "id", nullable = true)
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Yarn fringe;*/

    @Enumerated(EnumType.STRING)
    @Column(name = "weave_type", nullable = true, length = 20)
    private WeaveType weaveType;

    //Seyreltme Var mı?
    @Column(name = "making_rare", nullable = false, columnDefinition = "boolean default false")
    @NotNull(message = "{Seyreltme Var mı? Seçimini Yapmalısınız}")
    private boolean makingRare;

    //Seyreltme Rengi olarak 0ncı palet kullanılıyor. Birden fazla olması ihtimaline karşı bu fieldı oluşturdum.
    //Tire ile ayrılarak yazılmış palet numaraları buraya yazılabilir. (0,16,...)
    @Column(name = "making_rare_palette_indexes", nullable = true, length = 50)
    @ColumnDefault("'0-'")
    private String makingRarePaletteIndexes;

    @JoinColumn(name = "customer", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Firm customer;

    @JoinColumn(name = "design_plan", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private DesignPlan designPlan;

    @JoinColumn(name = "designer", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private UserAccount designer;

    @OneToMany(mappedBy = "archive", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<ArchiveToUserFile> archiveToUserFiles;

    @OneToMany(mappedBy = "archive", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<PixelRatio> pixelRatios;
}
    
