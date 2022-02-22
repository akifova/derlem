package com.kazu.carp.supplyChain.production.definition.stockCard.business;

import com.kazu.carp.app.jpa.specification.SpecCreator;
import com.kazu.carp.supplyChain.production.definition.stockCard.domain.Edge;
import com.kazu.carp.supplyChain.production.definition.stockCard.dto.EdgeDto;
import com.kazu.carp.supplyChain.production.definition.stockCard.dto.IStockCardDefinitionMapper;
import com.kazu.carp.supplyChain.production.definition.stockCard.persistence.IEdgeDao;
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
 * 10.02.2021
 */
@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackForClassName = {"Exception"})
public class EdgeServiceImpl implements IEdgeService {
    private static final Logger logger = LoggerFactory.getLogger(EdgeServiceImpl.class);

    final IEdgeDao dao;
    final IStockCardDefinitionMapper mapper;

    public EdgeServiceImpl(IEdgeDao dao, IStockCardDefinitionMapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    @Override
    public EdgeDto create(EdgeDto edgeDto) {
        Edge edge = mapper.toEntity(edgeDto);
        dao.save(edge);
        return mapper.toDto(edge);
    }

    @Override
    public void update(EdgeDto edgeDto) {
        Edge edge = dao.getById(edgeDto.getId());
        edge.setCode(edgeDto.getCode());
        edge.setDefinition(edgeDto.getDefinition());
        edge.setLength(edgeDto.getLength());
        dao.save(edge);
    }

    @Override
    public List<EdgeDto> findAll() {
        return dao.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public EdgeDto findOne(String id) {
        return mapper.toDto(dao.getById(id));
    }

    @Override
    public EdgeDto findFirstByCode(String code) {
        return mapper.toDto(dao.findFirstByCode(code));
    }

    @Override
    public List<EdgeDto> search(String sp) {
        var spec = new SpecCreator<Edge>(sp).getSpec();
        return dao.findAll(spec).stream().map(m-> mapper.toDto(m)).collect(Collectors.toList());
    }

    @Override
    public Edge getById(String id) {
        if(StringUtils.isEmpty(id)) {
            return null;
        }
        return dao.getById(id);
    }

    @Override
    public void delete(String id) {
        dao.deleteById(id);
    }
}