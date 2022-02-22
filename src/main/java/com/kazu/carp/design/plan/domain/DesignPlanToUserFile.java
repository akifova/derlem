package com.kazu.carp.design.plan.domain;

import com.kazu.carp.app.jpa.BaseEntity;
import com.kazu.carp.common.file.userFile.domain.UserFile;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.AttributeAccessor;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@AttributeAccessor(value = "field")
@Table(name = "design_plan_to_file", schema = "design",
        indexes = {@Index(name = "ix_design_plan_to_file_user_file", columnList = "user_file"),
                @Index(name = "ix_design_plan_to_file_design_plan", columnList = "design_plan")})
@Getter
@Setter
@NoArgsConstructor
@Audited
@SuperBuilder
public class DesignPlanToUserFile extends BaseEntity {

    @JoinColumn(name = "user_file", referencedColumnName = "id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private UserFile userFile;

    @JoinColumn(name = "design_plan", referencedColumnName = "id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private DesignPlan designPlan;
}