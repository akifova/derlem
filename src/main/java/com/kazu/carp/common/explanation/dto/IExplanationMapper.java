package com.kazu.carp.common.explanation.dto;

import com.kazu.carp.app.config.MapStructConfig;
import com.kazu.carp.common.explanation.domain.Explanation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.nio.charset.Charset;

/**
 * @author akifova
 * 14.02.2021
 */
@Mapper(config = MapStructConfig.class, imports = {Charset.class})
public interface IExplanationMapper {

    IExplanationMapper INSTANCE = Mappers.getMapper(IExplanationMapper.class);

    @Mappings({
            @Mapping(target = "text", expression = "java(explanationDto.getText().getBytes(Charset.forName(\"UTF-8\")))"),
            @Mapping(target = "backColor", defaultValue = "FFFFFF"),
            @Mapping(target = "likeCount", defaultExpression = "java((short) 0)"),
            @Mapping(target = "writer", ignore = true),
            @Mapping(target = "creelExplanations", ignore = true),
            @Mapping(target = "designPlans", ignore = true)
    })
    Explanation map(ExplanationDto explanationDto);

    @Mappings({
            @Mapping(target = "text", expression = "java(new String(explanation.getText()))"),
            @Mapping(target = "writer", ignore = true),
            @Mapping(target = "creelExplanations", ignore = true),
            @Mapping(target = "designPlans", ignore = true)
    })
    ExplanationDto map(Explanation explanation);
}