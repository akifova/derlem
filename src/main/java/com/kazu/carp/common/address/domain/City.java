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
@Table(name = "city", schema = "common",
        indexes = {
                @Index(name = "ix_city_name", columnList = "name"),
                @Index(name = "ix_city_region", columnList = "region")
})
@Getter
@Setter
@NoArgsConstructor
@Audited
public class City extends BaseEntity {

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "plate_number", nullable = false, length = 5)
    private String plateNumber;

    @JoinColumn(name = "country", referencedColumnName = "id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Country country;

    @JoinColumn(name = "region", referencedColumnName = "id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Region region;

    @OneToMany(mappedBy = "city", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<County> counties;
}
    
