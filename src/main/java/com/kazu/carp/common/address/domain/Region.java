package com.kazu.carp.common.address.domain;

import com.kazu.carp.app.jpa.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.AttributeAccessor;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.List;

//import com.sarparda.carp.ybt.domain.Ybt;

/**
 * Created by sarparda on 24.05.2017.
 */
@Entity
@AttributeAccessor(value = "field")
@Table(name = "region", schema = "common", uniqueConstraints = {@UniqueConstraint(columnNames = {"country", "name"})},
        indexes = {
            @Index(name = "ix_region_name", columnList = "name"),
                @Index(name = "ix_region_country", columnList = "name")
})
@Getter
@Setter
@NoArgsConstructor
@Audited
public class Region extends BaseEntity {

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @JoinColumn(name = "country", referencedColumnName = "id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Country country;

    @OneToMany(mappedBy = "region", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<City> cities;
}
    
