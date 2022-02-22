package com.kazu.carp.common.address.domain;

import com.kazu.carp.app.jpa.BaseEntity;
import com.kazu.carp.common.firm.domain.Firm;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.AttributeAccessor;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.List;

/**
 * Created by sarparda on 10.08.2016.
 */
@Entity
@AttributeAccessor(value = "field")
@Table(name = "address", schema = "common",
        indexes = {@Index(name = "ix_address_name", columnList = "name")})
@Getter
@Setter
@NoArgsConstructor
@Audited
public class Address extends BaseEntity {

    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @Column(name = "address", nullable = false, length = 300)
    private String address;

    @Column(name = "web_url", nullable = true, length = 100)
    private String webUrl;

    @JoinColumn(name = "county", referencedColumnName = "id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private County county;

    @OneToMany(mappedBy = "address", fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<Mail> mails;

    @OneToMany(mappedBy = "address", fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<Phone> phones ;

    @OneToMany(mappedBy = "address", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Firm> firms;
}
    
