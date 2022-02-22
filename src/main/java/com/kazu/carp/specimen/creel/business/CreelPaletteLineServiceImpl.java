package com.kazu.carp.specimen.creel.business;

import com.kazu.carp.design.color.business.IColorService;
import com.kazu.carp.design.color.dto.IDesignMapper;
import com.kazu.carp.specimen.creel.domain.CreelPalette;
import com.kazu.carp.specimen.creel.domain.CreelPaletteLine;
import com.kazu.carp.specimen.creel.domain.CreelPaletteLineToYarn;
import com.kazu.carp.specimen.creel.dto.*;
import com.kazu.carp.specimen.creel.persistence.ICreelPaletteLineDao;
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
public class CreelPaletteLineServiceImpl implements ICreelPaletteLineService {
    private static final Logger logger = LoggerFactory.getLogger(CreelPaletteLineServiceImpl.class);

    final ICreelPaletteLineDao dao;
    final ICreelMapper mapper;
    final ICreelPaletteService paletteService;
    final IYarnService yarnService;
    final ISampleYarnService sampleYarnService;
    final IYarnMapper yarnMapper;
    final ISampleYarnMapper sampleYarnMapper;
    final ICreelService creelService;
    final IColorService colorService;
    final IDesignMapper designMapper;

    public CreelPaletteLineServiceImpl(ICreelPaletteLineDao dao, ICreelMapper mapper, ICreelPaletteService paletteService, IYarnService yarnService, ISampleYarnService sampleYarnService, IYarnMapper yarnMapper, ISampleYarnMapper sampleYarnMapper, ICreelService creelService, IColorService colorService, IDesignMapper designMapper) {
        this.dao = dao;
        this.mapper = mapper;
        this.paletteService = paletteService;
        this.yarnService = yarnService;
        this.sampleYarnService = sampleYarnService;
        this.yarnMapper = yarnMapper;
        this.sampleYarnMapper = sampleYarnMapper;
        this.creelService = creelService;
        this.colorService = colorService;
        this.designMapper = designMapper;
    }

    @Override
    public CreelPaletteLineDto create(CreelPaletteLineDto creelPaletteLineDto) {
        CreelPalette palette = paletteService.getById(creelPaletteLineDto.getCreelPalette().getId());

        int paletteVersion = palette.getVersion() + 1;
        creelService.setCreelVersion(palette.getCreel(), 0, paletteVersion);
        palette.setVersionNumber((short) paletteVersion);

        CreelPaletteLine line = CreelPaletteLine.builder()
                .rotationNumber(creelPaletteLineDto.getRotationNumber())
                .color(colorService.getFromDto(creelPaletteLineDto.getColor()))
                .creelPalette(palette)
                .build();

        List<CreelPaletteLineToYarn> lattes = creelPaletteLineDto.getLineToYarns().stream()
                .map(m-> {
                    Yarn yarn = m.getYarn() != null ? yarnService.getById(m.getYarn().getId()) : null;
                    SampleYarn sampleYarn = m.getSampleYarn() != null ? sampleYarnService.getById(m.getSampleYarn().getId()) : null;
                    CreelPaletteLineToYarn latte = CreelPaletteLineToYarn.builder()
                            .lattePercentage(m.getLattePercentage())
                            .creelPaletteLine(line)
                            .yarn(yarn)
                            .sampleYarn(sampleYarn)
                            .build();
                    return latte;
                }).collect(Collectors.toList());
        line.setLineToYarns(lattes);
        dao.save(line);

        CreelPaletteLineDto lineDto = convertEntityToDto(line);
        return lineDto;
    }

    @Override
    public void update(CreelPaletteLineDto creelPaletteLineDto) {
        CreelPaletteLine line = dao.getById(creelPaletteLineDto.getId());
        line.setRotationNumber(creelPaletteLineDto.getRotationNumber());
        line.setColor(colorService.getFromDto(creelPaletteLineDto.getColor()));
        line.getCreelPalette().setVersionNumber((short) (line.getCreelPalette().getVersionNumber() + 1));
        creelService.setCreelVersion(line.getCreelPalette().getCreel(), 0, line.getCreelPalette().getVersionNumber());
        dao.save(line);
    }

    @Override
    public List<CreelPaletteLineDto> findByPaletteId(String paletteId) {
        List<CreelPaletteLine> lines = dao.findByCreelPaletteId(paletteId);
        return lines.stream().map(m-> convertEntityToDto(m)).collect(Collectors.toList());
    }

    @Override
    public CreelPaletteLineDto findOne(String id) {
        return convertEntityToDto(dao.getById(id));
    }

    @Override
    public void delete(String id) {
        dao.deleteById(id);
    }

    @Override
    public CreelPaletteLine getById(String id) {
        return dao.getById(id);
    }

    CreelPaletteLineDto convertEntityToDto(CreelPaletteLine line) {
        CreelPaletteLineDto lineDto = mapper.toDto(line);

        CreelDto creelDto = mapper.toDto(line.getCreelPalette().getCreel());
        CreelPaletteDto paletteDto = mapper.toDto(line.getCreelPalette());
        paletteDto.setCreel(creelDto);

        lineDto.setCreelPalette(paletteDto);
        lineDto.setColor(designMapper.toDto(line.getColor()));
        lineDto.setLineToYarns(line.getLineToYarns().stream().map(m->{
            CreelPaletteLineToYarnDto dto = mapper.toDto(m);
            dto.setYarn(yarnMapper.toDto(m.getYarn()));
            dto.setSampleYarn(sampleYarnMapper.toDto(m.getSampleYarn()));
            return dto;
        }).collect(Collectors.toList()));

        return lineDto;
    }
}