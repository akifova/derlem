package com.kazu.carp.common.file.util.dto;

import com.kazu.carp.app.config.MapStructConfig;
import com.kazu.carp.common.file.userFile.domain.UserFile;
import com.kazu.carp.common.file.userFile.dto.UserFileDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @author akifova
 * 14.02.2021
 */
@Mapper(config = MapStructConfig.class)
public interface IUserFileMapper {

    @Mappings({
            @Mapping(target = "designPlanToFiles", ignore = true)
    })
    UserFile toEntity(UserFileDto userFileDto);

    @Mappings({
            @Mapping(target = "designPlanToFiles", ignore = true)
    })
    UserFileDto toDto(UserFile userFile);
}