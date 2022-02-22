package com.kazu.carp.yarn.definition.business;

import com.kazu.carp.app.exception.yarn.YarnPropertiesNotAsDefinedInYarnGroup;
import com.kazu.carp.app.jpa.specification.SpecCreator;
import com.kazu.carp.common.uom.business.IUOMService;
import com.kazu.carp.common.uom.dto.IUOMMapper;
import com.kazu.carp.yarn.definition.domain.Yarn;
import com.kazu.carp.yarn.definition.domain.YarnGroup;
import com.kazu.carp.yarn.definition.domain.YarnPropertyLine;
import com.kazu.carp.yarn.definition.domain.YarnToPropertyLine;
import com.kazu.carp.yarn.definition.dto.IYarnMapper;
import com.kazu.carp.yarn.definition.dto.YarnDto;
import com.kazu.carp.yarn.definition.dto.YarnToPropertyLineDto;
import com.kazu.carp.yarn.definition.persistence.IYarnDao;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author akifova
 * 05.05.2021
 */
@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackForClassName = {"Exception"})
public class YarnServiceImpl implements IYarnService {
    private static final Logger logger = LoggerFactory.getLogger(YarnServiceImpl.class);

    final IYarnDao dao;
    final IYarnMapper mapper;
    final IUOMMapper uomMapper;
    final IYarnGroupService yarnGroupService;
    final IYarnPropertyLineService propertyLineService;
    final IUOMService uomService;

    public YarnServiceImpl(IYarnDao dao, IYarnMapper mapper, IUOMMapper uomMapper, IYarnGroupService yarnGroupService,
                           IYarnPropertyLineService propertyLineService, IUOMService uomService) {
        this.dao = dao;
        this.mapper = mapper;
        this.uomMapper = uomMapper;
        this.yarnGroupService = yarnGroupService;
        this.propertyLineService = propertyLineService;
        this.uomService = uomService;
    }

    @Override
    public YarnDto create(YarnDto yarnDto) throws YarnPropertiesNotAsDefinedInYarnGroup {
        YarnGroup yarnGroup = yarnGroupService.getById(yarnDto.getYarnGroup().getId());
        Yarn yarn = Yarn.builder()
                .name(yarnDto.getName())
                .weight(yarnDto.getWeight())
                .actionType(yarnDto.getActionType())
                .minStock(yarnDto.getMinStock())
                .salable(yarnDto.getSalable())
                .needQcApprove(yarnDto.getNeedQcApprove())
                .surveillance(yarnDto.getSurveillance())
                .unitOfMeasure(uomService.getById(yarnDto.getUnitOfMeasure().getId()))
                .weightUnit(uomService.getById(yarnDto.getWeightUnit().getId()))
                .yarnGroup(yarnGroup)
                .usageType(yarnDto.getUsageType())
                .mark(yarnDto.getMark())
                .build();
        setPropertyLines(yarn, yarnGroup, yarnDto.getYarnToPropertyLines());
        dao.save(yarn);
        YarnDto dto = mapper.toDto(yarn);
        dto.setUnitOfMeasure(yarnDto.getUnitOfMeasure());
        dto.setWeightUnit(yarnDto.getWeightUnit());
        dto.setYarnGroup(yarnDto.getYarnGroup());
        return dto;
    }

    @Override
    public void update(YarnDto yarnDto) {
        Yarn yarn = dao.getById(yarnDto.getId());
        yarn.setName(yarnDto.getName());
        yarn.setWeight(yarnDto.getWeight());
        yarn.setActionType(yarnDto.getActionType());
        yarn.setMinStock(yarnDto.getMinStock());
        yarn.setSalable(yarnDto.getSalable());
        yarn.setNeedQcApprove(yarnDto.getNeedQcApprove());
        yarn.setSurveillance(yarnDto.getSurveillance());
        yarn.setUnitOfMeasure(uomService.getById(yarnDto.getUnitOfMeasure().getId()));
        yarn.setWeightUnit(uomService.getById(yarnDto.getWeightUnit().getId()));
        yarn.setYarnGroup(yarnGroupService.getById(yarnDto.getYarnGroup().getId()));
        yarn.setUsageType(yarnDto.getUsageType());
        yarn.setMark(yarn.getMark());
        dao.save(yarn);
    }

    @Override
    public List<YarnDto> findAll() {
        return dao.findAll().stream().map(m-> {
            YarnDto yarnDto = mapper.toDto(m);
            yarnDto.setUnitOfMeasure(uomMapper.toDto(m.getUnitOfMeasure()));
            yarnDto.setWeightUnit(uomMapper.toDto(m.getWeightUnit()));
            yarnDto.setYarnGroup(mapper.toDto(m.getYarnGroup()));
            return yarnDto;
        }).collect(Collectors.toList());
    }

    @Override
    public YarnDto findOne(String id) {
        Yarn yarn = dao.getById(id);
        YarnDto yarnDto = mapper.toDto(yarn);
        yarnDto.setUnitOfMeasure(uomMapper.toDto(yarn.getUnitOfMeasure()));
        yarnDto.setWeightUnit(uomMapper.toDto(yarn.getWeightUnit()));
        yarnDto.setYarnGroup(mapper.toDto(yarn.getYarnGroup()));
        return yarnDto;
    }

    @Override
    public void delete(String id) {
        dao.deleteById(id);
    }

    @Override
    public Yarn getById(String id) {
        if(StringUtils.isEmpty(id)) {
            return null;
        }
        return dao.getById(id);
    }

    @Override
    public List<YarnDto> search(String sp) {
        var spec = new SpecCreator<Yarn>(sp).getSpec();
        return dao.findAll(spec).stream().map(m-> mapper.toDto(m)).collect(Collectors.toList());
    }

    private void setPropertyLines(Yarn yarn, YarnGroup yarngroup, List<YarnToPropertyLineDto> propertyLines) throws YarnPropertiesNotAsDefinedInYarnGroup {
        if(yarngroup.getProperties() == null || yarngroup.getProperties().size() == 0) {
            yarn.setYarnToPropertyLines(null);
            return;
        }
        var due = yarngroup.getProperties().stream()
                .map(m-> m.getYarnProperty().getId())
                .collect(Collectors.toList());

        if(propertyLines == null || propertyLines.size() <=0 || due.size() != propertyLines.size()) {
            throw new YarnPropertiesNotAsDefinedInYarnGroup();
        }

        List<YarnToPropertyLine> lines = propertyLines.stream().map(pl-> {
            YarnPropertyLine ypl = propertyLineService.getById(pl.getYarnPropertyLine().getId());
            YarnToPropertyLine toPropertyLine = YarnToPropertyLine.builder()
                    .yarnPropertyLine(ypl)
                    .yarn(yarn)
                    .build();

            return toPropertyLine;
        }).collect(Collectors.toList());

        List<String> propertyIds = lines.stream().map(m-> m.getYarnPropertyLine().getYarnProperty().getId()).collect(Collectors.toList());

        if(!due.equals(propertyIds)) {
            throw new YarnPropertiesNotAsDefinedInYarnGroup();
        }
        yarn.setYarnToPropertyLines(lines);

    }
}