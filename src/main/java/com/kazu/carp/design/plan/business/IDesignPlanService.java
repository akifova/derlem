package com.kazu.carp.design.plan.business;

import com.kazu.carp.design.plan.domain.DesignPlan;
import com.kazu.carp.design.plan.dto.DesignPlanDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author akifova
 * 14.02.2021
 */
public interface IDesignPlanService {
    DesignPlanDto create(DesignPlanDto designPlanDto, List<MultipartFile> files);

    void update(DesignPlanDto designPlanDto);

    List<DesignPlanDto> findAll();

    DesignPlanDto findOne(String id);

    DesignPlan getById(String id);

    List<DesignPlanDto> findActives();

    List<DesignPlanDto> search(String searchTerm);

    void close(String planId);
}
