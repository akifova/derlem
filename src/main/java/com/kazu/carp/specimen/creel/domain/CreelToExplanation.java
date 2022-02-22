package com.kazu.carp.specimen.creel.domain;

import com.kazu.carp.app.jpa.BaseEntity;
import com.kazu.carp.common.explanation.domain.Explanation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.AttributeAccessor;
import org.hibernate.envers.Audited;

import javax.persistence.*;


/**
 * @author akif.ova
 * @since 28.07.2020
 */
@Entity
@AttributeAccessor(value = "field")
@Table(name = "creel_to_explanation", schema = "specimen",
        indexes = {@Index(name = "ix_sample_creel_to_explanation_creel", columnList = "creel")})
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Audited
public class CreelToExplanation extends BaseEntity {

    @JoinColumn(name = "creel", referencedColumnName = "id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Creel creel;

    @JoinColumn(name = "explanation", referencedColumnName = "id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private Explanation explanation;

}
    
