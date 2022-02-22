package com.kazu.carp.yarn.definition.business;

import com.kazu.carp.yarn.definition.domain.YarnProperty;
import com.kazu.carp.yarn.definition.dto.IYarnMapper;
import com.kazu.carp.yarn.definition.dto.YarnPropertyDto;
import com.kazu.carp.yarn.definition.persistence.IYarnPropertyDao;
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
public class YarnPropertyServiceImpl implements IYarnPropertyService {
    private static final Logger logger = LoggerFactory.getLogger(YarnPropertyServiceImpl.class);

    final IYarnPropertyDao dao;
    final IYarnMapper mapper;

    public YarnPropertyServiceImpl(IYarnPropertyDao dao, IYarnMapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    @Override
    public YarnPropertyDto create(YarnPropertyDto yarnPropertyDto) {
        YarnProperty yp = YarnProperty.builder()
                .name(yarnPropertyDto.getName())
                .yarnPropertyType(yarnPropertyDto.getYarnPropertyType())
                .printOnBarcode(yarnPropertyDto.getPrintOnBarcode())
                .build();
        dao.save(yp);
        return mapper.toDto(yp);
    }

    @Override
    public void update(YarnPropertyDto yarnPropertyDto) {
        YarnProperty yp = dao.getById(yarnPropertyDto.getId());
        yp.setName(yarnPropertyDto.getName());
        yp.setYarnPropertyType(yarnPropertyDto.getYarnPropertyType());
        yp.setPrintOnBarcode(yarnPropertyDto.getPrintOnBarcode());
        dao.save(yp);
    }

    @Override
    public List<YarnPropertyDto> findAll() {
        return dao.findAll().stream().map(m-> mapper.toDto(m)).collect(Collectors.toList());
    }

    @Override
    public YarnPropertyDto findOne(String id) {
        return mapper.toDto(dao.getById(id));
    }

    @Override
    public void delete(String id) {
        dao.deleteById(id);
    }

    @Override
    public YarnProperty getById(String id) {
        return dao.getById(id);
    }
}