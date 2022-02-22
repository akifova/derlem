package com.kazu.carp.common.address.domain;


import com.kazu.carp.app.jpa.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.AttributeAccessor;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.List;

/**
 * Created by sarparda on 31.07.2016.
 */
@Entity
@AttributeAccessor(value = "field")
@Table(name = "county", schema = "common", uniqueConstraints = {@UniqueConstraint(columnNames = {"city", "name"})},
        indexes = { @Index(name = "ix_county_name", columnList = "name"), @Index(name = "ix_county_city", columnList = "city")})
@Getter
@Setter
@NoArgsConstructor
@Audited
public class County extends BaseEntity {

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "county_code", nullable = false, length = 100)
    private String countyCode;

    @JoinColumn(name = "city", referencedColumnName = "id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private City city;

    @OneToMany(mappedBy = "county", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Address> addresses;
}
    
