package com.kazu.carp.design.archive.domain;

import com.kazu.carp.app.jpa.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.AttributeAccessor;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;


/**
 * @author akifova
 * 07.06.2021
 */
@Entity
@AttributeAccessor(value = "field")
@Table(name = "pixel_ratio", schema = "design")
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Audited
public class PixelRatio extends BaseEntity implements Serializable {
    @Column(name = "version_number", nullable = true, columnDefinition = "SMALLINT default 0")
    private short versionNumber;

    @Column(name = "version_date", nullable = true)
    //@Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime versionDate;

    @Column(name = "palette_no", nullable = false)
    private Integer paletteNo;

    @Column(name = "count", nullable = false)
    private Long count;

    @Column(name = "rgb_red", nullable = false)
    private Integer rgbRed;

    @Column(name = "rgb_green", nullable = false)
    private Integer rgbGreen;

    @Column(name = "rgb_blue", nullable = false)
    private Integer rgbBlue;

    @Column(name = "ratio", precision = 10, scale = 2, nullable = false, columnDefinition = "DECIMAL(10,2) default 0.00")
    private BigDecimal ratio;

    @JoinColumn(name = "design_rd", referencedColumnName = "id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Archive archive;
}
    
