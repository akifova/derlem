package com.kazu.carp.supplyChain.production.definition.stockCard.business;

import com.kazu.carp.app.jpa.specification.SpecCreator;
import com.kazu.carp.design.color.business.IColorService;
import com.kazu.carp.design.color.dto.IDesignMapper;
import com.kazu.carp.supplyChain.production.definition.stockCard.domain.Palette;
import com.kazu.carp.supplyChain.production.definition.stockCard.domain.PaletteLine;
import com.kazu.carp.supplyChain.production.definition.stockCard.domain.PaletteLineToYarn;
import com.kazu.carp.supplyChain.production.definition.stockCard.dto.IStockCardDefinitionMapper;
import com.kazu.carp.supplyChain.production.definition.stockCard.dto.PaletteLineDto;
import com.kazu.carp.supplyChain.production.definition.stockCard.dto.PaletteLineToYarnDto;
import com.kazu.carp.supplyChain.production.definition.stockCard.persistence.IPaletteLineDao;
import com.kazu.carp.yarn.definition.dto.IYarnMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author akifova
 * 07.02.2021
 */
@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackForClassName = {"Exception"})
public class PaletteLineServiceImpl implements IPaletteLineService {
    private static final Logger logger = LoggerFactory.getLogger(PaletteLineServiceImpl.class);

    final IPaletteLineDao dao;
    final IStockCardDefinitionMapper mapper;
    final IPaletteService paletteService;
    final IColorService colorService;
    final IDesignMapper designMapper;
    final IYarnMapper yarnMapper;

    public PaletteLineServiceImpl(IPaletteLineDao dao, IStockCardDefinitionMapper mapper, IPaletteService paletteService, IColorService colorService, IDesignMapper designMapper, IYarnMapper yarnMapper) {
        this.dao = dao;
        this.mapper = mapper;
        this.paletteService = paletteService;
        this.colorService = colorService;
        this.designMapper = designMapper;
        this.yarnMapper = yarnMapper;
    }

    @Override
    public PaletteLineDto create(PaletteLineDto paletteLineDto) {
        Palette palette = paletteService.getById(paletteLineDto.getPalette().getId());
        List<PaletteLineToYarn> lattes = new ArrayList<>();

        PaletteLine line = mapper.toEntity(paletteLineDto);
        paletteLineDto.getLineToYarns().stream().forEach(l->{
            PaletteLineToYarn latte = PaletteLineToYarn.builder()
                    .lattePercentage(l.getLattePercentage())
                    .paletteLine(line)
                    .build();
            lattes.add(latte);
        });

        line.setPalette(palette);
        line.setColor(colorService.getFromDto(paletteLineDto.getColor()));
        line.setLineToYarns(lattes);
        dao.save(line);
        return mapper.toDto(line);
    }

    @Override
    public void update(PaletteLineDto paletteLineDto) {
        PaletteLine line = dao.getById(paletteLineDto.getId());

        if(paletteLineDto.getColor().getRgbRed() != paletteLineDto.getColor().getRgbRed() ||
                line.getColor().getRgbGreen() != paletteLineDto.getColor().getRgbGreen() ||
                line.getColor().getRgbBlue() != paletteLineDto.getColor().getRgbBlue()) {
            line.setColor(colorService.getFromDto(paletteLineDto.getColor()));
        }

        line.setRotationNumber(paletteLineDto.getRotationNumber());
        dao.save(line);
    }

    @Override
    public List<PaletteLineDto> findAll() {
        return dao.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public PaletteLineDto findOne(String id) {
        return mapper.toDto(dao.getById(id));
    }

    @Override
    public PaletteLine getById(String id) {
        return dao.getById(id);
    }

    @Override
    public void delete(String id) {
        dao.deleteById(id);
    }

    @Override
    public List<PaletteLineDto> search(String sp) {
        var spec = new SpecCreator<PaletteLine>(sp).getSpec();
        return dao.findAll(spec).stream().map(m-> {
            var dto = mapper.toDto(m);
            dto.setPalette(mapper.toDto(m.getPalette()));
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public List<PaletteLineDto> findByPaletteId(String paletteId) {
        List<PaletteLine> lines = dao.findByPaletteId(paletteId);

        return lines.stream()
                .map(l-> {
                    PaletteLineDto dto = mapper.toDto(l);
                    dto.setColor(designMapper.toDto(l.getColor()));
                    List<PaletteLineToYarnDto> lattes = l.getLineToYarns().stream()
                            .map(s-> {
                                var lineToYarnDto = mapper.toDto(s);
                                lineToYarnDto.setYarn(yarnMapper.toDto(s.getYarn()));
                                return lineToYarnDto;
                            }).collect(Collectors.toList());
                    dto.setLineToYarns(lattes);
                    return dto;
                }).collect(Collectors.toList());
    }
}