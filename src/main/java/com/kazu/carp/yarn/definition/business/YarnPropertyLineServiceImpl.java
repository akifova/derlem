package com.kazu.carp.yarn.definition.business;

import com.kazu.carp.yarn.definition.domain.YarnProperty;
import com.kazu.carp.yarn.definition.domain.YarnPropertyLine;
import com.kazu.carp.yarn.definition.dto.IYarnMapper;
import com.kazu.carp.yarn.definition.dto.YarnPropertyLineDto;
import com.kazu.carp.yarn.definition.persistence.IYarnPropertyLineDao;
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
public class YarnPropertyLineServiceImpl implements IYarnPropertyLineService {
    private static final Logger logger = LoggerFactory.getLogger(YarnPropertyLineServiceImpl.class);

    final IYarnPropertyLineDao dao;
    final IYarnPropertyService propertyService;
    final IYarnMapper mapper;

    public YarnPropertyLineServiceImpl(IYarnPropertyLineDao dao, IYarnPropertyService propertyService, IYarnMapper mapper) {
        this.dao = dao;
        this.propertyService = propertyService;
        this.mapper = mapper;
    }

    @Override
    public YarnPropertyLineDto create(YarnPropertyLineDto yarnPropertyLineDto) {
        YarnProperty yp = propertyService.getById(yarnPropertyLineDto.getYarnProperty().getId());
        YarnPropertyLine yarnPropertyLine = YarnPropertyLine.builder()
                .attribute(yarnPropertyLineDto.getAttribute())
                .code(yarnPropertyLineDto.getCode())
                .yarnProperty(yp)
                .build();
        dao.save(yarnPropertyLine);

        YarnPropertyLineDto dto = mapper.toDto(yarnPropertyLine);
        dto.setYarnProperty(mapper.toDto(yp));

        return dto;
    }

    @Override
    public void update(YarnPropertyLineDto yarnPropertyLineDto) {
        YarnProperty yp = propertyService.getById(yarnPropertyLineDto.getYarnProperty().getId());
        YarnPropertyLine yarnPropertyLine = dao.getById(yarnPropertyLineDto.getId());

        yarnPropertyLine.setAttribute(yarnPropertyLineDto.getAttribute());
        yarnPropertyLine.setCode(yarnPropertyLineDto.getCode());
        yarnPropertyLine.setYarnProperty(yp);

        dao.save(yarnPropertyLine);
    }

    @Override
    public List<YarnPropertyLineDto> findAll() {
        return dao.findAll().stream().map(m-> {
            YarnPropertyLineDto dto = mapper.toDto(m);
            dto.setYarnProperty(mapper.toDto(m.getYarnProperty()));
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public YarnPropertyLineDto findOne(String id) {
        YarnPropertyLine pl = dao.getById(id);
        YarnPropertyLineDto dto = mapper.toDto(pl);
        dto.setYarnProperty(mapper.toDto(pl.getYarnProperty()));
        return dto;
    }

    @Override
    public void delete(String id) {
        dao.deleteById(id);
    }

    @Override
    public YarnPropertyLine getById(String id) {
        return dao.getById(id);
    }

    @Override
    public List<YarnPropertyLineDto> getByPropertyId(String propertyId) {
        List<YarnPropertyLine> lines = dao.findByYarnPropertyId(propertyId);
        return lines.stream().map(m-> mapper.toDto(m)).collect(Collectors.toList());
    }
}