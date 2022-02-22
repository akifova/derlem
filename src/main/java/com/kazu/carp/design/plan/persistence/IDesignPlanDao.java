package com.kazu.carp.design.plan.persistence;

import com.kazu.carp.design.plan.domain.DesignPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author akifova
 * 14.02.2021
 */
public interface IDesignPlanDao extends JpaRepository<DesignPlan, String>, JpaSpecificationExecutor<DesignPlan> {
    List<DesignPlan> findByActive(boolean b);

    List<DesignPlan> findByTitleContainingIgnoreCase(String searchTerm);
}
