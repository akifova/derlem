package com.kazu.carp.supplyChain.production.definition.stockCard.business;

import com.kazu.carp.app.jpa.specification.SpecCreator;
import com.kazu.carp.design.color.business.IColorService;
import com.kazu.carp.design.color.dto.IDesignMapper;
import com.kazu.carp.supplyChain.production.definition.stockCard.domain.PaletteLineToYarn;
import com.kazu.carp.supplyChain.production.definition.stockCard.dto.IStockCardDefinitionMapper;
import com.kazu.carp.supplyChain.production.definition.stockCard.dto.PaletteLineDto;
import com.kazu.carp.supplyChain.production.definition.stockCard.dto.PaletteLineToYarnDto;
import com.kazu.carp.supplyChain.production.definition.stockCard.persistence.IPaletteLineLatteDao;
import com.kazu.carp.yarn.definition.business.IYarnService;
import com.kazu.carp.yarn.definition.dto.IYarnMapper;
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
 * 08.02.2021
 */
@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackForClassName = {"Exception"})
public class PaletteLineToYarnServiceImpl implements IPaletteLineToYarnService {
    private static final Logger logger = LoggerFactory.getLogger(PaletteLineToYarnServiceImpl.class);

    final IPaletteLineLatteDao dao;
    final IStockCardDefinitionMapper mapper;
    final IColorService colorService;
    final IPaletteLineService paletteLineService;
    final IDesignMapper designMapper;
    final IYarnService yarnService;
    final IYarnMapper yarnMapper;

    public PaletteLineToYarnServiceImpl(IPaletteLineLatteDao dao, IStockCardDefinitionMapper mapper, IColorService colorService, IPaletteLineService paletteLineService, IDesignMapper designMapper, IYarnService yarnService, IYarnMapper yarnMapper) {
        this.dao = dao;
        this.mapper = mapper;
        this.colorService = colorService;
        this.paletteLineService = paletteLineService;
        this.designMapper = designMapper;
        this.yarnService = yarnService;
        this.yarnMapper = yarnMapper;
    }

    @Override
    public PaletteLineToYarnDto create(PaletteLineToYarnDto latteDto) {
        PaletteLineToYarn latte = mapper.toEntity(latteDto);
        latte.setYarn(yarnService.getById(latteDto.getYarn().getId()));
        latte.setPaletteLine(paletteLineService.getById(latteDto.getPaletteLine().getId()));
        dao.save(latte);
        PaletteLineToYarnDto dto = mapper.toDto(latte);
        return dto;
    }

    @Override
    public void update(PaletteLineToYarnDto latteDto) {
        PaletteLineToYarn latte = dao.getById(latteDto.getId());
        latte.setLattePercentage(latteDto.getLattePercentage());
        latte.setYarn(yarnService.getById(latteDto.getYarn().getId()));

        dao.save(latte);
    }

    @Override
    public List<PaletteLineToYarnDto> findAll() {
        return dao.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public PaletteLineToYarnDto findOne(String id) {
        return mapper.toDto(dao.getById(id));
    }

    @Override
    public void delete(String id) {
        dao.deleteById(id);
    }

    @Override
    public List<PaletteLineToYarnDto> search(String sp) {
        var spec = new SpecCreator<PaletteLineToYarn>(sp).getSpec();
        return dao.findAll(spec).stream().map(m-> {
            var dto = mapper.toDto(m);
            PaletteLineDto line = mapper.toDto(m.getPaletteLine());
            line.setPalette(mapper.toDto(m.getPaletteLine().getPalette()));
            dto.setYarn(yarnMapper.toDto(m.getYarn()));
            dto.setPaletteLine(line);
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public PaletteLineToYarn getById(String id) {
        if(StringUtils.isEmpty(id)) {
            return null;
        }
        return dao.getById(id);
    }
}