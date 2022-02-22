package com.kazu.carp.common.address.domain;

import com.kazu.carp.app.jpa.BaseEntity;
import com.kazu.carp.common.address.dto.PhoneType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.AttributeAccessor;
import org.hibernate.envers.Audited;

import javax.persistence.*;

/**
 * Created by sarparda on 10.08.2016.
 */
@Entity
@AttributeAccessor(value = "field")
@Table(name = "phone", schema = "common",
        indexes = {@Index(name = "ix_phone_address", columnList = "address")})
@Getter
@Setter
@NoArgsConstructor
@Audited
public class Phone extends BaseEntity {

    @Column(name = "phone_number", nullable = false, length = 30)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "phone_type", nullable = false)
    private PhoneType phoneType;

    @JoinColumn(name = "address", referencedColumnName = "id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Address address;


}
    
