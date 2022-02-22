package com.kazu.carp.yarn.definition.business;

import com.kazu.carp.yarn.definition.domain.YarnType;
import com.kazu.carp.yarn.definition.dto.IYarnMapper;
import com.kazu.carp.yarn.definition.dto.YarnTypeDto;
import com.kazu.carp.yarn.definition.persistence.IYarnTypeDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author akifova
 * 01.05.2021
 */
@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackForClassName = {"Exception"})
public class YarnTypeServiceImpl implements IYarnTypeService {
    private static final Logger logger = LoggerFactory.getLogger(YarnTypeServiceImpl.class);

    final IYarnTypeDao dao;
    final IYarnMapper mapper;

    public YarnTypeServiceImpl(IYarnTypeDao dao, IYarnMapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    @Override
    public YarnTypeDto create(YarnTypeDto yarnTypeDto) {
        YarnType yarnType = YarnType.builder().name(yarnTypeDto.getName()).build();
        dao.save(yarnType);
        return mapper.toDto(yarnType);
    }

    @Override
    public void update(YarnTypeDto yarnTypeDto) {
        YarnType yarnType = dao.getById(yarnTypeDto.getId());
        yarnType.setName(yarnTypeDto.getName());
        dao.save(yarnType);
    }

    @Override
    public List<YarnTypeDto> findAll() {
        return dao.findAll().stream().map(m->mapper.toDto(m)).collect(Collectors.toList());
    }

    @Override
    public YarnTypeDto findOne(String id) {
        return mapper.toDto(dao.getById(id));
    }

    @Override
    public void delete(String id) {
        dao.deleteById(id);
    }

    @Override
    public YarnType getById(String id) {
        return dao.getById(id);
    }
}