package com.kazu.carp.specimen.creel.business;

import com.kazu.carp.specimen.creel.domain.Creel;
import com.kazu.carp.specimen.creel.domain.CreelPalette;
import com.kazu.carp.specimen.creel.domain.CreelPaletteLine;
import com.kazu.carp.specimen.creel.domain.CreelPaletteLineToYarn;
import com.kazu.carp.specimen.creel.dto.CreelPaletteDto;
import com.kazu.carp.specimen.creel.dto.CreelPaletteLineDto;
import com.kazu.carp.specimen.creel.dto.CreelPaletteLineToYarnDto;
import com.kazu.carp.specimen.creel.dto.ICreelMapper;
import com.kazu.carp.specimen.creel.persistence.ICreelPaletteLineLatteDao;
import com.kazu.carp.specimen.yarn.business.ISampleYarnService;
import com.kazu.carp.specimen.yarn.domain.SampleYarn;
import com.kazu.carp.specimen.yarn.dto.ISampleYarnMapper;
import com.kazu.carp.yarn.definition.business.IYarnService;
import com.kazu.carp.yarn.definition.domain.Yarn;
import com.kazu.carp.yarn.definition.dto.IYarnMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author akifova
 * 12.05.2021
 */
@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackForClassName = {"Exception"})
public class CreelPaletteLineToYarnServiceImpl implements ICreelPaletteLineToYarnService {
    private static final Logger logger = LoggerFactory.getLogger(CreelPaletteLineToYarnServiceImpl.class);

    final ICreelPaletteLineLatteDao dao;
    final ICreelMapper mapper;
    final ICreelPaletteLineService lineService;
    final IYarnService yarnService;
    final ISampleYarnService sampleYarnService;
    final IYarnMapper yarnMapper;
    final ISampleYarnMapper sampleYarnMapper;
    final ICreelService creelService;

    public CreelPaletteLineToYarnServiceImpl(ICreelPaletteLineLatteDao dao, ICreelMapper mapper,
                                             ICreelPaletteLineService lineService, IYarnService yarnService,
                                             ISampleYarnService sampleYarnService, IYarnMapper yarnMapper,
                                             ISampleYarnMapper sampleYarnMapper, ICreelService creelService) {
        this.dao = dao;
        this.mapper = mapper;
        this.lineService = lineService;
        this.yarnService = yarnService;
        this.sampleYarnService = sampleYarnService;
        this.yarnMapper = yarnMapper;
        this.sampleYarnMapper = sampleYarnMapper;
        this.creelService = creelService;
    }

    @Override
    public CreelPaletteLineToYarnDto create(CreelPaletteLineToYarnDto creelPaletteLineToYarnDto) {
        CreelPaletteLine line = lineService.getById(creelPaletteLineToYarnDto.getCreelPaletteLine().getId());

        CreelPalette palette = line.getCreelPalette();
        Creel creel = palette.getCreel();
        palette.setVersionNumber((short) (palette.getVersionNumber() + 1));
        creelService.setCreelVersion(creel, 0, palette.getVersionNumber());

        Yarn yarn = yarnService.getById(creelPaletteLineToYarnDto.getYarn().getId());
        SampleYarn sampleYarn = sampleYarnService.getById(creelPaletteLineToYarnDto.getSampleYarn().getId());

        CreelPaletteLineToYarn latte = CreelPaletteLineToYarn.builder()
                .lattePercentage(creelPaletteLineToYarnDto.getLattePercentage())
                .creelPaletteLine(line)
                .yarn(yarn)
                .sampleYarn(sampleYarn)
                .build();

        dao.save(latte);

        CreelPaletteLineToYarnDto dto = convertEntityToDto(latte);

        return dto;
    }

    @Override
    public CreelPaletteLineToYarnDto update(CreelPaletteLineToYarnDto creelPaletteLineToYarnDto) {
        CreelPaletteLineToYarn latte = dao.getById(creelPaletteLineToYarnDto.getId());
        latte.setLattePercentage(creelPaletteLineToYarnDto.getLattePercentage());
        CreelPalette palette = latte.getCreelPaletteLine().getCreelPalette();
        Creel creel = palette.getCreel();
        palette.setVersionNumber((short) (palette.getVersionNumber() + 1));
        creelService.setCreelVersion(creel, 0, palette.getVersionNumber());
        dao.save(latte);

        CreelPaletteLineToYarnDto dto = convertEntityToDto(latte);
        return dto;
    }

    @Override
    public List<CreelPaletteLineToYarnDto> findByPaletteLineId(String lineId) {
        List<CreelPaletteLineToYarn> lattes = dao.findByCreelPaletteLineId(lineId);
        return lattes.stream().map(m-> convertEntityToDto(m)).collect(Collectors.toList());
    }

    @Override
    public CreelPaletteLineToYarnDto findOne(String id) {
        return convertEntityToDto(dao.getById(id));
    }

    @Override
    public void delete(String id) {
        dao.deleteById(id);
    }

    private CreelPaletteLineToYarnDto convertEntityToDto(CreelPaletteLineToYarn latte) {
        CreelPaletteLineToYarnDto dto = mapper.toDto(latte);
        CreelPaletteDto paletteDto = mapper.toDto(latte.getCreelPaletteLine().getCreelPalette());
        paletteDto.setCreel(mapper.toDto(latte.getCreelPaletteLine().getCreelPalette().getCreel()));
        CreelPaletteLineDto lineDto = mapper.toDto(latte.getCreelPaletteLine());
        lineDto.setCreelPalette(paletteDto);
        dto.setCreelPaletteLine(lineDto);

        return dto;
    }
}