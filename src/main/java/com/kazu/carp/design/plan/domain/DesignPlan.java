package com.kazu.carp.design.plan.domain;

import com.kazu.carp.app.jpa.BaseEntity;
import com.kazu.carp.common.explanation.domain.Explanation;
import com.kazu.carp.security.account.domain.UserAccount;
import com.kazu.carp.specimen.creel.domain.Creel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.AttributeAccessor;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@AttributeAccessor(value = "field")
@Table(name = "design_plan", schema = "design", uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})})

@Getter
@Setter
@NoArgsConstructor
@Audited
@SuperBuilder
public class DesignPlan extends BaseEntity {

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(name = "dead_line", nullable = false)
    private LocalDate deadLine;

    @JoinColumn(name = "explanation", referencedColumnName = "id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Explanation task;

    @JoinColumn(name = "owner", referencedColumnName = "id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private UserAccount owner;

    @OneToMany(mappedBy = "designPlan", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Creel> creels;

    @OneToMany(mappedBy = "designPlan", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<DesignPlanToUserFile> designPlanToFiles;
}
    
