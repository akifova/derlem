package com.kazu.carp.common.explanation.domain;

import com.kazu.carp.app.jpa.BaseEntity;
import com.kazu.carp.design.plan.domain.DesignPlan;
import com.kazu.carp.security.account.domain.UserAccount;
import com.kazu.carp.specimen.creel.domain.CreelToExplanation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.AttributeAccessor;
import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.List;

@Entity
@AttributeAccessor(value = "field")
@Table(schema = "common", name = "explanation")
@Getter
@Setter
@NoArgsConstructor
@Audited
@SuperBuilder
public class Explanation extends BaseEntity {

    @Column(name = "text_line", nullable = false, length = 8000)
    @Lob
    @Type(type="org.hibernate.type.BinaryType")
    private byte[] text;

    @Column(name = "back_color", nullable = false, length = 100)
    private String backColor;

    @Column(name = "like_count", nullable = false, columnDefinition = "SMALLINT default 0")
    private short likeCount;

    @JoinColumn(name = "comment", referencedColumnName = "id", nullable = true)
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Explanation parentNote;

    @JoinColumn(name = "writer", referencedColumnName = "id", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private UserAccount writer;

    @OneToMany(mappedBy = "parentNote", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private List<Explanation> comments;

    @OneToMany(mappedBy = "task", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<DesignPlan> designPlans;

    @OneToMany(mappedBy = "explanation", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<CreelToExplanation> creelExplanations;
}
    
