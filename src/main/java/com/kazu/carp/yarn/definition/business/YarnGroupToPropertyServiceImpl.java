package com.kazu.carp.yarn.definition.business;

import com.kazu.carp.yarn.definition.domain.YarnGroup;
import com.kazu.carp.yarn.definition.domain.YarnGroupToProperty;
import com.kazu.carp.yarn.definition.domain.YarnProperty;
import com.kazu.carp.yarn.definition.dto.IYarnMapper;
import com.kazu.carp.yarn.definition.dto.YarnGroupToPropertyDto;
import com.kazu.carp.yarn.definition.persistence.IYarnGroupToPropertyDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author akifova
 * 06.05.2021
 */
@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackForClassName = {"Exception"})
public class YarnGroupToPropertyServiceImpl implements IYarnGroupToPropertyService {
    private static final Logger logger = LoggerFactory.getLogger(YarnGroupToPropertyServiceImpl.class);

    final IYarnGroupToPropertyDao dao;
    final IYarnMapper mapper;
    final IYarnGroupService groupService;
    final IYarnPropertyService propertyService;

    public YarnGroupToPropertyServiceImpl(IYarnGroupToPropertyDao dao, IYarnMapper mapper, IYarnGroupService groupService,
                                          IYarnPropertyService propertyService) {
        this.dao = dao;
        this.mapper = mapper;
        this.groupService = groupService;
        this.propertyService = propertyService;
    }

    @Override
    public YarnGroupToPropertyDto findOne(String id) {
        return mapper.toDto(dao.getById(id));
    }

    @Override
    public List<YarnGroupToPropertyDto> findByGroupId(String groupId) {
        List<YarnGroupToProperty> props = dao.findByYarnGroupId(groupId);
        return props.stream().map(m-> {
            YarnGroupToPropertyDto dto = new YarnGroupToPropertyDto();
            dto.setYarnGroup(mapper.toDto(m.getYarnGroup()));
            dto.setYarnProperty(mapper.toDto(m.getYarnProperty()));
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public void delete(String id) {
        dao.deleteById(id);
    }

    @Override
    public YarnGroupToPropertyDto create(YarnGroupToPropertyDto yarnGroupToPropertyDto) {
        YarnProperty property = propertyService.getById(yarnGroupToPropertyDto.getYarnProperty().getId());
        YarnGroup group = groupService.getById(yarnGroupToPropertyDto.getYarnGroup().getId());
        YarnGroupToProperty groupToProperty = YarnGroupToProperty.builder()
                .yarnProperty(property)
                .yarnGroup(group)
                .build();

        dao.save(groupToProperty);
        yarnGroupToPropertyDto.setId(groupToProperty.getId());
        yarnGroupToPropertyDto.setInsertDate(groupToProperty.getInsertDate());
        yarnGroupToPropertyDto.setVersion(groupToProperty.getVersion());
        yarnGroupToPropertyDto.setActive(true);
        return yarnGroupToPropertyDto;
    }
}