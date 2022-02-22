package com.kazu.carp.supplyChain.production.definition.stockCard.business;

import com.kazu.carp.app.jpa.specification.SpecCreator;
import com.kazu.carp.supplyChain.production.definition.stockCard.domain.Size;
import com.kazu.carp.supplyChain.production.definition.stockCard.dto.IStockCardDefinitionMapper;
import com.kazu.carp.supplyChain.production.definition.stockCard.dto.SizeDto;
import com.kazu.carp.supplyChain.production.definition.stockCard.dto.SizeType;
import com.kazu.carp.supplyChain.production.definition.stockCard.persistence.ISizeDao;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author akifova
 * 10.02.2021
 */
@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackForClassName = {"Exception"})
public class SizeServiceImpl implements ISizeService {
    private static final Logger logger = LoggerFactory.getLogger(SizeServiceImpl.class);

    final ISizeDao dao;
    final IStockCardDefinitionMapper mapper;

    public SizeServiceImpl(ISizeDao dao, IStockCardDefinitionMapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    @Override
    public SizeDto create(SizeDto sizeDto) {
        Size size = mapper.toEntity(sizeDto);
        size.setSizeType(getSizeType(size.getLength()));
        size.setSqm(calculateSqm(sizeDto.getWidth(), sizeDto.getLength()));
        size.setInch(calculateInch(sizeDto.getWidth(), sizeDto.getLength()));
        size.setDefinition(getDefinition(sizeDto.getWidth(), sizeDto.getLength()));
        dao.save(size);
        return mapper.toDto(size);
    }

    @Override
    public void update(SizeDto sizeDto) {
        Size size = dao.getById(sizeDto.getId());
        size.setSizeType(getSizeType(size.getLength()));
        size.setCode(sizeDto.getCode());
        size.setDefinition(getDefinition(sizeDto.getWidth(), sizeDto.getLength()));
        size.setWidth(sizeDto.getWidth());
        size.setLength(sizeDto.getLength());
        size.setSqm(calculateSqm(sizeDto.getWidth(), sizeDto.getLength()));
        size.setInch(calculateInch(sizeDto.getWidth(), sizeDto.getLength()));
        dao.save(size);
    }

    private SizeType getSizeType(int length) {
        return length > 1500 ? SizeType.roll : SizeType.standard;
    }

    private BigDecimal calculateSqm(int width, int length) {
        return BigDecimal.valueOf(width * length / 10000);
    }

    private BigDecimal calculateInch(int width, int length) {
        return BigDecimal.valueOf((width * length / 10000) / 2.54);
    }

    private String getDefinition(int width, int length) {
        String _width = String.format("%03d", width);
        String _length = String.format("%04d", length);
        return String.format("%sX%s", _width, _length);
    }

    @Override
    public List<SizeDto> findAll() {
        return dao.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public SizeDto findOne(String id) {
        return mapper.toDto(dao.getById(id));
    }

    @Override
    public void delete(String id) {
        dao.deleteById(id);
    }

    @Override
    public List<SizeDto> search(String sp) {
        var spec = new SpecCreator<Size>(sp).getSpec();
        return dao.findAll(spec).stream().map(m-> mapper.toDto(m)).collect(Collectors.toList());
    }

    @Override
    public Size getById(String id) {
        if(StringUtils.isEmpty(id)) {
            return null;
        }
        return dao.getById(id);
    }
}