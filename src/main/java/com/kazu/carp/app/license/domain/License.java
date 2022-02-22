package com.kazu.carp.app.license.domain;

import com.kazu.carp.app.jpa.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.AttributeAccessor;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * @author akifova
 * 05.10.2021
 */
@Entity
@AttributeAccessor(value = "field")
@Table(name = "license", schema = "sec",
        indexes = {@Index(name = "ix_license_end_date", columnList = "end_date")})
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Audited
public class License extends BaseEntity {

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "mac_address", nullable = false, length = 20)
    private String macAddress;

    @Column(name = "processor_id", nullable = false, length = 50)
    private String processorId;

    @Column(name = "base_board_serial_number", nullable = false, length = 50)
    private String baseBoardSerialNumber;

    @Column(name = "license", nullable = false, length = 500)
    private String license;
}
    
