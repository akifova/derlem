package com.kazu.carp.common.firm.domain;

import com.kazu.carp.app.jpa.BaseEntity;
import com.kazu.carp.common.address.domain.Address;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.AttributeAccessor;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;


/**
 * @author akifova
 * 29.04.2021
 */
@Entity
@AttributeAccessor(value = "field")
@Table(name = "firm", schema = "common", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})},
        indexes = {
            @Index(name = "ix_firm_name", columnList = "name"),
                @Index(name = "ix_firm_supplier", columnList = "is_supplier"),
                @Index(name = "ix_firm_customer", columnList = "is_customer"),
        })
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Audited
public class Firm extends BaseEntity implements Serializable {

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "trade_name", nullable = false, length = 100)
    private String tradeName;

    @Column(name = "tax_office", nullable = true, length = 50)
    private String taxOffice;

    @Column(name = "tax_number", nullable = true, length = 15)
    private String taxNumber;

    //Tedarikçi
    @Column(name = "is_supplier", nullable = false)
    private boolean supplier;

    //Müşteri
    @Column(name = "is_customer", nullable = false)
    private boolean customer;

    //Fason
    @Column(name = "is_outsourcer", nullable = false)
    private boolean outsourcer;

    //Şahıs Şirketi Mi?
    @Column(name = "is_person_company", nullable = false, columnDefinition = "SMALLINT default 0")
    private short isPersonCompany;

    @JoinColumn(name = "address", referencedColumnName = "id", nullable = true)
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, optional = true)
    private Address address;
}
    
