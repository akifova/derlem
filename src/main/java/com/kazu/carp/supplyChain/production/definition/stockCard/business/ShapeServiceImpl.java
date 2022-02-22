package com.kazu.carp.supplyChain.production.definition.stockCard.business;

import com.kazu.carp.app.jpa.specification.SpecCreator;
import com.kazu.carp.supplyChain.production.definition.stockCard.domain.Shape;
import com.kazu.carp.supplyChain.production.definition.stockCard.dto.IStockCardDefinitionMapper;
import com.kazu.carp.supplyChain.production.definition.stockCard.dto.ShapeDto;
import com.kazu.carp.supplyChain.production.definition.stockCard.persistence.IShapeDao;
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
 * 11.02.2021
 */
@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackForClassName = {"Exception"})
public class ShapeServiceImpl implements IShapeService {
    private static final Logger logger = LoggerFactory.getLogger(ShapeServiceImpl.class);

    final IShapeDao dao;
    final IStockCardDefinitionMapper mapper;

    public ShapeServiceImpl(IShapeDao dao, IStockCardDefinitionMapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    @Override
    public ShapeDto create(ShapeDto shapeDto) {
        Shape shape = mapper.toEntity(shapeDto);
        dao.save(shape);
        return mapper.toDto(shape);
    }

    @Override
    public void update(ShapeDto shapeDto) {
        Shape shape = dao.getById(shapeDto.getId());
        shape.setCode(shapeDto.getCode());
        shape.setDefinition(shapeDto.getDefinition());
        dao.save(shape);
    }

    @Override
    public List<ShapeDto> findAll() {
        return dao.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public ShapeDto findOne(String id) {
        return mapper.toDto(dao.getById(id));
    }

    @Override
    public ShapeDto findFirstByCode(String code) {
        return mapper.toDto(dao.findFirstByCode(code));
    }

    @Override
    public void delete(String id) {
        dao.deleteById(id);
    }

    @Override
    public List<ShapeDto> search(String sp) {
        var spec = new SpecCreator<Shape>(sp).getSpec();
        return dao.findAll(spec).stream().map(m-> mapper.toDto(m)).collect(Collectors.toList());
    }

    @Override
    public Shape getById(String id) {
        if(StringUtils.isEmpty(id)) {
            return null;
        }
        return dao.getById(id);
    }
}