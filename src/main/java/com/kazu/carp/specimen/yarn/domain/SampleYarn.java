package com.kazu.carp.specimen.yarn.domain;

import com.kazu.carp.app.jpa.BaseEntity;
import com.kazu.carp.common.firm.domain.Firm;
import com.kazu.carp.common.uom.domain.UnitOfMeasure;
import com.kazu.carp.specimen.yarn.dto.SampleYarnUV;
import com.kazu.carp.yarn.definition.domain.YarnGroup;
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
@Table(name = "sample_yarn", schema = "specimen")
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Audited
public class SampleYarn extends BaseEntity {

    @Column(name = "code", length = 30)
    private String code;

    @Column(name = "weight", nullable = false, length = 20)
    private String weight;

    @Column(name = "version_number", nullable = true, columnDefinition = "INT default 1")
    private int versionNumber;

    @Column(name = "manufacturer_code", nullable = false, length = 30)
    private String manufacturerCode;

    @Column(name = "color_code", nullable = false, length = 50)
    private String colorCode;

    @Column(name = "color_name", nullable = false, length = 50)
    private String colorName;

    @Column(name = "yarn_number", length = 50)
    private String yarnNumber;

    @Column(name = "spin", nullable = true, columnDefinition = "INT default 0")
    private int spin;

    @Column(name = "cips_percentage", precision = 10, scale = 2, nullable = true, columnDefinition = "DECIMAL(10,2) default 0.00")
    private BigDecimal cipsPercentage;

    @Enumerated(EnumType.STRING)
    @Column(name = "uv", nullable = true, length = 10)
    private SampleYarnUV uvType;

    @Column(name = "fixed_type", length = 50)
    private String fixedType;

    @Column(name = "fiber_type", length = 50)
    private String fiberType;

    @Column(name = "fiber_fineness", length = 10)
    private String fiberFineness;

    @Column(name = "filament_quantity", length = 10)
    private String filamentQuantity;

    @JoinColumn(name = "supplier", referencedColumnName = "id", nullable = true)
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Firm supplier;

    //Ağırlık birimi
    @JoinColumn(name = "weight_unit", referencedColumnName = "id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private UnitOfMeasure weightUnit;

    @JoinColumn(name = "main_sample_yarn", referencedColumnName = "id", nullable = true)
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private SampleYarn mainSampleYarn;

    @JoinColumn(name = "yarn_group", referencedColumnName = "id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private YarnGroup yarnGroup;

    @OneToMany(mappedBy = "mainSampleYarn", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<SampleYarn> sampleYarnVersions;
}
