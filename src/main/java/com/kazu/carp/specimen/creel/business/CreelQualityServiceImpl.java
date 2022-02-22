package com.kazu.carp.specimen.creel.business;

import com.kazu.carp.specimen.creel.domain.Creel;
import com.kazu.carp.specimen.creel.domain.CreelQuality;
import com.kazu.carp.specimen.creel.dto.CreelQualityDto;
import com.kazu.carp.specimen.creel.dto.ICreelMapper;
import com.kazu.carp.specimen.creel.persistence.ICreelQualityDao;
import com.kazu.carp.yarn.definition.business.IYarnService;
import com.kazu.carp.yarn.definition.domain.Yarn;
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
 * 09.05.2021
 */
@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackForClassName = {"Exception"})
public class CreelQualityServiceImpl implements ICreelQualityService {
    private static final Logger logger = LoggerFactory.getLogger(CreelQualityServiceImpl.class);

    final ICreelQualityDao dao;
    final IYarnService yarnService;
    final ICreelMapper mapper;
    final IYarnMapper yarnMapper;
    final ICreelService creelService;

    public CreelQualityServiceImpl(ICreelQualityDao dao, IYarnService yarnService, ICreelMapper mapper, IYarnMapper yarnMapper, ICreelService creelService) {
        this.dao = dao;
        this.yarnService = yarnService;
        this.mapper = mapper;
        this.yarnMapper = yarnMapper;
        this.creelService = creelService;
    }

    @Override
    public CreelQualityDto update(CreelQualityDto creelQualityDto) {
        CreelQuality quality = dao.getById(creelQualityDto.getId());
        Creel creel = quality.getCreel();

        quality.setVersionNumber((short) (quality.getVersionNumber() + 1));
        quality.setWoof(creelQualityDto.getWoof());
        quality.setCrest(creelQualityDto.getCrest());
        quality.setLancet(creelQualityDto.getLancet());
        quality.setKnittingType(creelQualityDto.getKnittingType());
        setYarnsToCreelQuality(creelQualityDto.getBottomWoofYarn().getId(), creelQualityDto.getMiddleWoofYarn().getId(),
                creelQualityDto.getTopWoofYarn().getId(), quality);

        creelService.setCreelVersion(creel, quality.getVersionNumber(), 0);

        dao.save(quality);
        CreelQualityDto dto = convertEntityToDto(quality);
        return dto;
    }

    @Override
    public List<CreelQualityDto> findByCreelId(String creelId) {
        List<CreelQuality> qualities = dao.findByCreelId(creelId);
        return qualities.stream().map(m-> convertEntityToDto(m)).collect(Collectors.toList());
    }

    @Override
    public CreelQualityDto findOne(String id) {
        return convertEntityToDto(dao.getById(id));
    }

    @Override
    public CreelQuality getById(String id) {
        return dao.getById(id);
    }

    private void setYarnsToCreelQuality(String bottomId, String middleId, String topId, CreelQuality creelQuality) {
        Yarn bottomWoofYarn = yarnService.getById(bottomId);
        Yarn middleWoofYarn = yarnService.getById(middleId);
        Yarn topWoofYarn = yarnService.getById(topId);

        creelQuality.setBottomWoofYarn(bottomWoofYarn);
        creelQuality.setMiddleWoofYarn(middleWoofYarn);
        creelQuality.setTopWoofYarn(topWoofYarn);
    }

    private CreelQualityDto convertEntityToDto(CreelQuality quality) {
        CreelQualityDto dto = mapper.toDto(quality);
        dto.setBottomWoofYarn(yarnMapper.toDto(quality.getBottomWoofYarn()));
        dto.setMiddleWoofYarn(yarnMapper.toDto(quality.getMiddleWoofYarn()));
        dto.setTopWoofYarn(yarnMapper.toDto(quality.getTopWoofYarn()));
        return dto;
    }
}