package com.kazu.carp.supplyChain.production.definition.stockCard.business;

import com.kazu.carp.app.jpa.specification.SpecCreator;
import com.kazu.carp.supplyChain.production.definition.stockCard.domain.Design;
import com.kazu.carp.supplyChain.production.definition.stockCard.dto.DesignDto;
import com.kazu.carp.supplyChain.production.definition.stockCard.dto.IStockCardDefinitionMapper;
import com.kazu.carp.supplyChain.production.definition.stockCard.persistence.IDesignDao;
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
 * 07.02.2021
 */
@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackForClassName = {"Exception"})
public class DesignServiceImpl implements IDesignService {
    private static final Logger logger = LoggerFactory.getLogger(DesignServiceImpl.class);

    final IDesignDao dao;
    final IStockCardDefinitionMapper mapper;

    public DesignServiceImpl(IDesignDao dao, IStockCardDefinitionMapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    @Override
    public DesignDto create(DesignDto designDto) {
        Design design = mapper.toEntity(designDto);
        dao.save(design);
        return mapper.toDto(design);
    }

    @Override
    public void update(DesignDto designDto) {
        Design design = dao.getById(designDto.getId());
        design.setCode(designDto.getCode());
        dao.save(design);
    }

    @Override
    public List<DesignDto> findAll() {
        return dao.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public DesignDto findOne(String id) {
        return mapper.toDto(dao.getById(id));
    }

    @Override
    public DesignDto findFirstByCode(String code) {
        return dao.findFirstByCode(code);
    }

    @Override
    public void delete(String id) {
        Design design = dao.getById(id);
        dao.delete(design);
    }

    @Override
    public List<DesignDto> search(String sp) {
        var spec = new SpecCreator<Design>(sp).getSpec();
        return dao.findAll(spec).stream().map(m-> mapper.toDto(m)).collect(Collectors.toList());
    }

    @Override
    public Design getById(String id) {
        if(StringUtils.isEmpty(id)) {
            return null;
        }
        return dao.getById(id);
    }
}