package com.kazu.carp.supplyChain.production.definition.stockCard.business;

import com.kazu.carp.app.jpa.specification.SpecCreator;
import com.kazu.carp.supplyChain.production.definition.stockCard.domain.Quality;
import com.kazu.carp.supplyChain.production.definition.stockCard.dto.IStockCardDefinitionMapper;
import com.kazu.carp.supplyChain.production.definition.stockCard.dto.QualityDto;
import com.kazu.carp.supplyChain.production.definition.stockCard.persistence.IQualityDao;
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
 * 06.02.2021
 */
@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackForClassName = {"Exception"})
public class QualityServiceImpl implements IQualityService {
    private static final Logger logger = LoggerFactory.getLogger(QualityServiceImpl.class);

    final IQualityDao dao;
    final IStockCardDefinitionMapper mapper;

    public QualityServiceImpl(IQualityDao dao, IStockCardDefinitionMapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    @Override
    public QualityDto create(QualityDto qualityDto) {
        Quality quality = mapper.toEntity(qualityDto);
        dao.save(quality);
        return mapper.toDto(quality);
    }

    @Override
    public void update(QualityDto qualityDto) {
        Quality quality = dao.getById(qualityDto.getId());
        quality.setCode(qualityDto.getCode());
        quality.setCrest(qualityDto.getCrest());
        quality.setWoof(qualityDto.getWoof());
        quality.setDefinition(qualityDto.getDefinition());
        dao.save(quality);
    }

    @Override
    public List<QualityDto> findAll() {
        List<Quality> qualities = dao.findAll();
        return qualities.stream().map(m-> mapper.toDto(m)).collect(Collectors.toList());
    }

    @Override
    public QualityDto findOne(String qualityId) {
        return mapper.toDto(dao.getById(qualityId));
    }

    @Override
    public QualityDto findFirstByCode(String code) {
        return mapper.toDto(dao.findFirstByCode(code));
    }

    @Override
    public void delete(String id) {
        Quality quality = dao.getById(id);
        if(quality != null) {
            dao.delete(quality);
        }
    }

    @Override
    public void deleteByCode(String code) {
        Quality quality = dao.findFirstByCode(code);
        dao.delete(quality);
    }

    @Override
    public List<QualityDto> search(String sp) {
        var spec = new SpecCreator<Quality>(sp).getSpec();
        return dao.findAll(spec).stream().map(m-> mapper.toDto(m)).collect(Collectors.toList());
    }

    @Override
    public Quality getById(String id) {
        if(StringUtils.isEmpty(id)) {
            return null;
        }
        return dao.getById(id);
    }
}