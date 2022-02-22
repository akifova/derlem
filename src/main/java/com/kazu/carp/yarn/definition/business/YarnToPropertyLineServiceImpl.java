package com.kazu.carp.yarn.definition.business;

import com.kazu.carp.yarn.definition.domain.Yarn;
import com.kazu.carp.yarn.definition.domain.YarnPropertyLine;
import com.kazu.carp.yarn.definition.domain.YarnToPropertyLine;
import com.kazu.carp.yarn.definition.dto.IYarnMapper;
import com.kazu.carp.yarn.definition.dto.YarnToPropertyLineDto;
import com.kazu.carp.yarn.definition.persistence.IYarnToPropertyLineDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author akifova
 * 06.05.2021
 */
@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackForClassName = {"Exception"})
public class YarnToPropertyLineServiceImpl implements IYarnToPropertyLineService {
    private static final Logger logger = LoggerFactory.getLogger(YarnToPropertyLineServiceImpl.class);

    final IYarnToPropertyLineDao dao;
    final IYarnService yarnService;
    final IYarnPropertyLineService lineService;
    final IYarnMapper mapper;

    public YarnToPropertyLineServiceImpl(IYarnToPropertyLineDao dao, IYarnService yarnService,
                                         IYarnPropertyLineService lineService, IYarnMapper mapper) {
        this.dao = dao;
        this.yarnService = yarnService;
        this.lineService = lineService;
        this.mapper = mapper;
    }

    @Override
    public YarnToPropertyLineDto update(YarnToPropertyLineDto yarnToPropertyLineDto) {
        YarnPropertyLine ypl = lineService.getById(yarnToPropertyLineDto.getYarnPropertyLine().getId());
        YarnToPropertyLine yarnToPropertyLine =
                dao.findFirstByYarnIdAndYarnPropertyLineYarnPropertyId(yarnToPropertyLineDto.getYarn().getId(), ypl.getYarnProperty().getId());
        Yarn yarn = yarnService.getById(yarnToPropertyLineDto.getYarn().getId());

        if(yarnToPropertyLine == null) {
            yarnToPropertyLine = YarnToPropertyLine.builder()
                    .yarnPropertyLine(ypl)
                    .yarn(yarn)
                    .build();
        } else {
            yarnToPropertyLine.setYarnPropertyLine(ypl);
        }
        dao.save(yarnToPropertyLine);
        yarnToPropertyLineDto.setId(yarnToPropertyLine.getId());
        return yarnToPropertyLineDto;
    }

    @Override
    public YarnToPropertyLineDto findOne(String id) {
        YarnToPropertyLine ytpl = dao.getById(id);
        YarnToPropertyLineDto dto = mapper.toDto(ytpl);
        dto.setYarnPropertyLine(mapper.toDto(ytpl.getYarnPropertyLine()));
        dto.setYarn(mapper.toDto(ytpl.getYarn()));
        return dto;
    }

    @Override
    public List<YarnToPropertyLineDto> getByYarnId(String yarnId) {
        List<YarnToPropertyLine> yarnToPropertyLines = dao.findByYarnId(yarnId);
        return yarnToPropertyLines.stream().map(m-> {
            YarnToPropertyLineDto dto = mapper.toDto(m);
            dto.setYarn(mapper.toDto(m.getYarn()));
            var propertyLine = mapper.toDto(m.getYarnPropertyLine());
            propertyLine.setYarnProperty(mapper.toDto(m.getYarnPropertyLine().getYarnProperty()));
            dto.setYarnPropertyLine(propertyLine);
            return dto;
        }).collect(Collectors.toList());
    }
}