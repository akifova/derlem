package com.kazu.carp.yarn.definition.domain;

import com.kazu.carp.app.jpa.BaseEntity;
import com.kazu.carp.common.uom.domain.UnitOfMeasure;
import com.kazu.carp.specimen.creel.domain.CreelPaletteLineToYarn;
import com.kazu.carp.specimen.creel.domain.CreelQuality;
import com.kazu.carp.supplyChain.production.definition.stockCard.domain.PaletteLineToYarn;
import com.kazu.carp.yarn.definition.dto.YarnActionType;
import com.kazu.carp.yarn.definition.dto.YarnUsageType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.AttributeAccessor;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@AttributeAccessor(value = "field")
@Table(name = "yarn", schema = "yarn", uniqueConstraints = {@UniqueConstraint(columnNames = {"yarn_group", "name"})},
        indexes = {@Index(name = "ix_yarn_name", columnList = "name")})
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Audited
public class Yarn extends BaseEntity {

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "weight", nullable = false, length = 20)
    private String weight;

    @Enumerated(EnumType.STRING)
    @Column(name = "action_type", nullable = false, length = 100)
    private YarnActionType actionType;

    @Column(name = "min_stock", precision = 10, scale = 2, nullable = false, columnDefinition = "DECIMAL(10,2) default 0.00")
    private BigDecimal minStock;

    @Column(name = "salable", nullable = false, columnDefinition = "SMALLINT default 0")
    private short salable;

    //Kalite Kontrol Onayı Olmadan Stok Girişi yapılabilir mi?
    @Column(name = "need_qc_approve", nullable = false, columnDefinition = "SMALLINT default 0")
    private short needQcApprove;

    //Stok Takibi Yapılacak mı?
    @Column(name = "surveillance", nullable = false, columnDefinition = "SMALLINT default 0")
    private short surveillance;

    //Satın alma ve takip birimi
    @JoinColumn(name = "unit_of_measure", referencedColumnName = "id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private UnitOfMeasure unitOfMeasure;

    //Ağırlık birimi
    @JoinColumn(name = "weight_unit", referencedColumnName = "id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private UnitOfMeasure weightUnit;

    @JoinColumn(name = "yarn_group", referencedColumnName = "id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private YarnGroup yarnGroup;

    //Kullanımda mı?  Aktif/Pasif iplik durumu
    @Enumerated(EnumType.STRING)
    @Column(name = "usage_type", nullable = false, length = 25)
    private YarnUsageType usageType;

    //Kullanıcının Ayırıştırıcı Tanımlamaları. Kolleksiyon adı yazmak gibi.
    @Column(name = "user_mark", nullable = true, length = 100)
    private String mark;

    @OneToMany(mappedBy = "yarn", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private List<YarnToPropertyLine> yarnToPropertyLines;

    @OneToMany(mappedBy = "yarn", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<PaletteLineToYarn> paletteLineToYarns;

    @OneToMany(mappedBy = "yarn", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<CreelPaletteLineToYarn> creelPaletteLineToYarns;

    @OneToMany(mappedBy = "bottomWoofYarn", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<CreelQuality> bottomWoofYarn;

    @OneToMany(mappedBy = "middleWoofYarn", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<CreelQuality> middleWoofYarn;

    @OneToMany(mappedBy = "topWoofYarn", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<CreelQuality> topWoofYarn;
}
    
