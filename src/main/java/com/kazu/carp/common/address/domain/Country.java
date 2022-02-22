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
 * Created by sarparda on 02.08.2016.
 */
@Entity
@AttributeAccessor(value = "field")
@Table(name = "country", schema = "common", uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "numeric_code", "alpha2_code", "alpha3_code"})},
        indexes = {@Index(name = "ix_country_name", columnList = "name")})
@Getter
@Setter
@NoArgsConstructor
@Audited
public class Country extends BaseEntity {

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "numeric_code", nullable = true, length = 100)
    private String numericCode;

    @Column(name = "alpha2_code", nullable = true, length = 100)
    private String alpha2Code;

    @Column(name = "alpha3_code", nullable = true, length = 100)
    private String alpha3Code;

    @OneToMany(mappedBy = "country", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Region> regions;

    @OneToMany(mappedBy = "country", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<City> cities ;
}
    
