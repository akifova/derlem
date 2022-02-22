package com.kazu.carp.common.explanation.business;

import com.kazu.carp.common.explanation.domain.Explanation;
import com.kazu.carp.common.explanation.dto.ExplanationDto;

/**
 * @author akifova
 * 14.02.2021
 */
public interface IExplanationService {
    Explanation getExplanationEntity(ExplanationDto explanationDto);

    ExplanationDto getExplanationDto(Explanation explanation, boolean includeComments);
}
