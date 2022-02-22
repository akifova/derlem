package com.kazu.carp.common.uom.business;

import com.kazu.carp.app.jpa.specification.SpecCreator;
import com.kazu.carp.common.uom.domain.UnitOfMeasure;
import com.kazu.carp.common.uom.dto.IUOMMapper;
import com.kazu.carp.common.uom.dto.UnitOfMeasureDto;
import com.kazu.carp.common.uom.persistence.IUOMDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author akifova
 * 29.04.2021
 */
@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackForClassName = {"Exception"})
public class UOMServiceImpl implements IUOMService {
    private static final Logger logger = LoggerFactory.getLogger(UOMServiceImpl.class);

    final IUOMDao dao;
    final IUOMMapper mapper;

    public UOMServiceImpl(IUOMDao dao, IUOMMapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    @Override
    public UnitOfMeasureDto create(UnitOfMeasureDto unitOfMeasureDto) {
        UnitOfMeasure uom = UnitOfMeasure.builder()
                .unit(unitOfMeasureDto.getUnit())
                .shortName(unitOfMeasureDto.getShortName())
                .build();
        dao.save(uom);
        return mapper.toDto(uom);
    }

    @Override
    public void update(UnitOfMeasureDto unitOfMeasureDto) {
        UnitOfMeasure uom = dao.getById(unitOfMeasureDto.getId());
        uom.setUnit(unitOfMeasureDto.getUnit());
        uom.setShortName(unitOfMeasureDto.getShortName());
        dao.save(uom);
    }

    @Override
    public List<UnitOfMeasureDto> findAll() {
        return dao.findAll().stream().map(m->mapper.toDto(m)).collect(Collectors.toList());
    }

    @Override
    public UnitOfMeasureDto findOne(String id) {
        return mapper.toDto(dao.getById(id));
    }

    @Override
    public void delete(String id) {
        dao.deleteById(id);
    }

    @Override
    public List<UnitOfMeasureDto> findByNameLike(String searchTerm) {
        return dao.findByUnitLike("%" + searchTerm + "%").stream().map(m->mapper.toDto(m)).collect(Collectors.toList());
    }

    @Override
    public UnitOfMeasure getById(String id) {
        return dao.getById(id);
    }

    @Override
    public List<UnitOfMeasureDto> search(String sp) {
        var spec = new SpecCreator<UnitOfMeasure>(sp).getSpec();
        return dao.findAll(spec).stream().map(m-> mapper.toDto(m)).collect(Collectors.toList());
    }
}