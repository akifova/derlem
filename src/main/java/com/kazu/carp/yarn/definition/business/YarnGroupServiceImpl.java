package com.kazu.carp.yarn.definition.business;

import com.kazu.carp.app.jpa.specification.SpecCreator;
import com.kazu.carp.yarn.definition.domain.YarnGroup;
import com.kazu.carp.yarn.definition.domain.YarnGroupToProperty;
import com.kazu.carp.yarn.definition.domain.YarnProperty;
import com.kazu.carp.yarn.definition.domain.YarnType;
import com.kazu.carp.yarn.definition.dto.IYarnMapper;
import com.kazu.carp.yarn.definition.dto.YarnGroupDto;
import com.kazu.carp.yarn.definition.dto.YarnGroupToPropertyDto;
import com.kazu.carp.yarn.definition.persistence.IYarnGroupDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author akifova
 * 01.05.2021
 */
@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackForClassName = {"Exception"})
public class YarnGroupServiceImpl implements IYarnGroupService {
    private static final Logger logger = LoggerFactory.getLogger(YarnGroupServiceImpl.class);

    final IYarnGroupDao dao;
    final IYarnTypeService typeService;
    final IYarnMapper mapper;
    final IYarnPropertyService propertyService;

    public YarnGroupServiceImpl(IYarnGroupDao dao, IYarnTypeService typeService, IYarnMapper mapper, IYarnPropertyService propertyService) {
        this.dao = dao;
        this.typeService = typeService;
        this.mapper = mapper;
        this.propertyService = propertyService;
    }

    @Override
    public YarnGroupDto create(YarnGroupDto yarnGroupDto) {
        YarnType yarnType = typeService.getById(yarnGroupDto.getYarnType().getId());
        YarnGroup yarnGroup = YarnGroup.builder().name(yarnGroupDto.getName()).yarnType(yarnType).build();
        setProperties(yarnGroup, yarnGroupDto.getProperties());
        dao.save(yarnGroup);
        YarnGroupDto dto = mapper.toDto(yarnGroup);
        dto.setYarnType(mapper.toDto(yarnType));
        return dto;
    }

    @Override
    public void update(YarnGroupDto yarnGroupDto) {
        YarnType yarnType = typeService.getById(yarnGroupDto.getYarnType().getId());
        YarnGroup yarnGroup = dao.getById(yarnGroupDto.getId());
        yarnGroup.setName(yarnGroupDto.getName());
        yarnGroup.setYarnType(yarnType);
        dao.save(yarnGroup);
    }

    @Override
    public List<YarnGroupDto> findAll() {
        return dao.findAll().stream().map(m-> {
            YarnGroupDto dto = mapper.toDto(m);
            dto.setYarnType(mapper.toDto(m.getYarnType()));
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public YarnGroupDto findOne(String id) {
        YarnGroup yarnGroup = dao.getById(id);
        YarnGroupDto dto = mapper.toDto(yarnGroup);
        dto.setYarnType(mapper.toDto(yarnGroup.getYarnType()));
        return dto;
    }

    @Override
    public void delete(String id) {
        dao.deleteById(id);
    }

    @Override
    public YarnGroup getById(String id) {
        return dao.getById(id);
    }

    @Override
    public List<YarnGroupDto> search(String sp) {
        var spec = new SpecCreator<YarnGroup>(sp).getSpec();
        return dao.findAll(spec).stream().map(m-> {
            var dto = mapper.toDto(m);
            dto.setYarnType(mapper.toDto(m.getYarnType()));
            return dto;
        }).collect(Collectors.toList());
    }

    private void setProperties(YarnGroup yarnGroup, List<YarnGroupToPropertyDto> properties) {
        if(properties != null && properties.size() > 0) {
            List<YarnGroupToProperty> props = properties.stream().map(m-> {
                YarnProperty property = propertyService.getById(m.getYarnProperty().getId());
                return YarnGroupToProperty.builder()
                        .yarnProperty(property)
                        .yarnGroup(yarnGroup)
                        .build();
            }).collect(Collectors.toList());
            yarnGroup.setProperties(props);
        }
    }
}