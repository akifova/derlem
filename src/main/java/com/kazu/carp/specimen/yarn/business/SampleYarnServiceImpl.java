package com.kazu.carp.specimen.yarn.business;

import com.kazu.carp.app.jpa.specification.SpecCreator;
import com.kazu.carp.common.firm.business.IFirmService;
import com.kazu.carp.common.firm.dto.IFirmMapper;
import com.kazu.carp.common.uom.business.IUOMService;
import com.kazu.carp.common.uom.dto.IUOMMapper;
import com.kazu.carp.specimen.yarn.domain.SampleYarn;
import com.kazu.carp.specimen.yarn.dto.ISampleYarnMapper;
import com.kazu.carp.specimen.yarn.dto.SampleYarnDto;
import com.kazu.carp.specimen.yarn.persistence.ISampleYarnJpaDao;
import com.kazu.carp.yarn.definition.business.IYarnGroupService;
import com.kazu.carp.yarn.definition.dto.IYarnMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author akifova
 * 30.04.2021
 */
@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackForClassName = {"Exception"})
public class SampleYarnServiceImpl implements ISampleYarnService {
    private static final Logger logger = LoggerFactory.getLogger(SampleYarnServiceImpl.class);

    final ISampleYarnJpaDao jpaDao;
    final IFirmService firmService;
    final IYarnGroupService yarnGroupService;
    final IUOMService uomService;
    final ISampleYarnMapper mapper;
    final IUOMMapper uomMapper;
    final IFirmMapper firmMapper;
    final IYarnMapper yarnMapper;

    public SampleYarnServiceImpl(ISampleYarnJpaDao jpaDao, IFirmService firmService, IYarnGroupService yarnGroupService, IUOMService uomService,
                                 ISampleYarnMapper mapper, IUOMMapper uomMapper, IFirmMapper firmMapper, IYarnMapper yarnMapper) {
        this.jpaDao = jpaDao;
        this.firmService = firmService;
        this.yarnGroupService = yarnGroupService;
        this.uomService = uomService;
        this.mapper = mapper;
        this.uomMapper = uomMapper;
        this.firmMapper = firmMapper;
        this.yarnMapper = yarnMapper;
    }

    @Override
    public SampleYarnDto create(SampleYarnDto sampleYarnDto) {
        SampleYarn sy = createSampleYarnEntityFromDto(sampleYarnDto);
        jpaDao.save(sy);
        SampleYarnDto dto = mapper.toDto(sy);
        dto.setSupplier(firmMapper.toDto(sy.getSupplier()));
        dto.setWeightUnit(uomMapper.toDto(sy.getWeightUnit()));
        dto.setYarnGroup(yarnMapper.toDto(sy.getYarnGroup()));
        return dto;
    }

    @Override
    public SampleYarnDto update(SampleYarnDto sampleYarnDto) {
        SampleYarn originalSampleYarn = jpaDao.getById(sampleYarnDto.getId());
        originalSampleYarn.setActive(false);
        SampleYarn masterSampleYarn = null;
        int versionNumber = 0;

        if(originalSampleYarn.getMainSampleYarn() == null) {
            masterSampleYarn = originalSampleYarn;
            versionNumber = originalSampleYarn.getVersionNumber() + 1;
        } else {
            masterSampleYarn = jpaDao.getById(originalSampleYarn.getMainSampleYarn().getId());
            versionNumber = jpaDao.findByMainSampleYarnId(originalSampleYarn.getMainSampleYarn().getId())
            .stream().mapToInt(m-> m.getVersionNumber()).max().orElse(2);
        }

        SampleYarn sy = createSampleYarnEntityFromDto(sampleYarnDto);
        sy.setVersionNumber(versionNumber);
        sy.setMainSampleYarn(masterSampleYarn);
        jpaDao.save(sy);
        return mapper.toDto(sy);
    }

    private SampleYarn createSampleYarnEntityFromDto(SampleYarnDto sampleYarnDto) {
        SampleYarn sy = SampleYarn.builder()
                .code(sampleYarnDto.getCode())
                .weight(sampleYarnDto.getWeight())
                .versionNumber(1)
                .active(true)
                .manufacturerCode(sampleYarnDto.getManufacturerCode())
                .colorCode(sampleYarnDto.getColorCode())
                .colorName(sampleYarnDto.getColorName())
                .yarnNumber(sampleYarnDto.getYarnNumber())
                .fixedType(sampleYarnDto.getFixedType())
                .fiberType(sampleYarnDto.getFiberType())
                .fiberFineness(sampleYarnDto.getFiberFineness())
                .filamentQuantity(sampleYarnDto.getFilamentQuantity())
                .spin(sampleYarnDto.getSpin())
                .cipsPercentage(sampleYarnDto.getCipsPercentage())
                .uvType(sampleYarnDto.getUvType())
                .supplier(firmService.getById(sampleYarnDto.getSupplier().getId()))
                .weightUnit(uomService.getById(sampleYarnDto.getWeightUnit().getId()))
                .yarnGroup(yarnGroupService.getById(sampleYarnDto.getYarnGroup().getId()))
                .build();
        return sy;
    }

    @Override
    public List<SampleYarnDto> findAll() {
        return jpaDao.findByActive(true).stream().map(m-> {
            SampleYarnDto dto = mapper.toDto(m);
            dto.setSupplier(firmMapper.toDto(m.getSupplier()));
            dto.setWeightUnit(uomMapper.toDto(m.getWeightUnit()));
            dto.setYarnGroup(yarnMapper.toDto(m.getYarnGroup()));
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public SampleYarnDto findOne(String id) {
        SampleYarn sy = jpaDao.getById(id);
        SampleYarnDto dto = mapper.toDto(sy);
        dto.setSupplier(firmMapper.toDto(sy.getSupplier()));
        dto.setWeightUnit(uomMapper.toDto(sy.getWeightUnit()));
        dto.setYarnGroup(yarnMapper.toDto(sy.getYarnGroup()));
        return dto;
    }

    @Override
    public void delete(String id) {
        SampleYarn sy = jpaDao.getById(id);
        sy.setActive(false);
        jpaDao.save(sy);
    }

    @Override
    public SampleYarn getById(String id) {
        return jpaDao.getById(id);
    }

    @Override
    public List<SampleYarnDto> search(String sp) {
        var spec = new SpecCreator<SampleYarn>(sp).getSpec();
        return jpaDao.findAll(spec).stream().map(m->mapper.toDto(m)).collect(Collectors.toList());
    }
}