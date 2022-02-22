package com.kazu.carp.common.address.domain;

import com.kazu.carp.app.jpa.BaseEntity;
import com.kazu.carp.common.address.dto.MailType;
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
@Table(name = "mail", schema = "common",
        indexes = {@Index(name = "ix_mail_address", columnList = "address")})
@Getter
@Setter
@NoArgsConstructor
@Audited
public class Mail extends BaseEntity {

    @Column(name = "mail_address", nullable = false, length = 100)
    private String mailAddress;

    @Enumerated(EnumType.STRING)
    @Column(name = "mail_type", nullable = false)
    private MailType mailType;

    @JoinColumn(name = "address", referencedColumnName = "id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Address address;


}
    
