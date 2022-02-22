package com.kazu.carp.specimen.creel.business;

import com.kazu.carp.common.explanation.business.IExplanationService;
import com.kazu.carp.common.explanation.dto.IExplanationMapper;
import com.kazu.carp.design.color.business.IColorService;
import com.kazu.carp.design.color.dto.IDesignMapper;
import com.kazu.carp.design.plan.business.IDesignPlanService;
import com.kazu.carp.design.plan.domain.DesignPlan;
import com.kazu.carp.design.plan.dto.IDesignPlanMapper;
import com.kazu.carp.specimen.creel.domain.*;
import com.kazu.carp.specimen.creel.dto.*;
import com.kazu.carp.specimen.creel.persistence.ICreelDao;
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
 * 07.05.2021
 */
@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackForClassName = {"Exception"})
public class CreelServiceImpl implements ICreelService {
    private static final Logger logger = LoggerFactory.getLogger(CreelServiceImpl.class);

    final ICreelDao dao;
    final IDesignPlanService designPlanService;
    final IYarnService yarnService;
    final ICreelMapper mapper;
    final IExplanationService explanationService;
    final IExplanationMapper explanationMapper;
    final IDesignPlanMapper designPlanMapper;
    final IYarnMapper yarnMapper;
    final IColorService colorService;
    final IDesignMapper designMapper;

    public CreelServiceImpl(ICreelDao dao, IDesignPlanService designPlanService, IYarnService yarnService,
                            ICreelMapper mapper, IExplanationService explanationService,
                            IExplanationMapper explanationMapper, IDesignPlanMapper designPlanMapper, IYarnMapper yarnMapper, IColorService colorService, IDesignMapper designMapper) {
        this.dao = dao;
        this.designPlanService = designPlanService;
        this.yarnService = yarnService;
        this.mapper = mapper;
        this.explanationService = explanationService;
        this.explanationMapper = explanationMapper;
        this.designPlanMapper = designPlanMapper;
        this.yarnMapper = yarnMapper;
        this.colorService = colorService;
        this.designMapper = designMapper;
    }

    @Override
    public CreelDto create(CreelDto creelDto) {
        Creel creel = Creel.builder()
                .activeVersion("1.1")
                .paletteCode(creelDto.getPaletteCode())
                .qualityName(creelDto.getQualityName())
                .sampleName(creelDto.getSampleName())
                .raising(creelDto.getRaising())
                .washing(creelDto.getWashing())
                .build();
        DesignPlan designPlan = designPlanService.getById(creelDto.getDesignPlan().getId());

        //Kalite Tanımı Ekleniyor
        Yarn bottomWoofYarn = creelDto.getCreelQuality().getBottomWoofYarn() == null ? null : yarnService.getById(creelDto.getCreelQuality().getBottomWoofYarn().getId());
        Yarn middleWoofYarn = creelDto.getCreelQuality().getMiddleWoofYarn() == null ? null : yarnService.getById(creelDto.getCreelQuality().getMiddleWoofYarn().getId());
        Yarn topWoofYarn = creelDto.getCreelQuality().getTopWoofYarn() == null ? null : yarnService.getById(creelDto.getCreelQuality().getTopWoofYarn().getId());
        CreelQuality quality = CreelQuality.builder()
                .versionNumber((short) 1)
                .woof(creelDto.getCreelQuality().getWoof())
                .crest(creelDto.getCreelQuality().getCrest())
                .lancet(creelDto.getCreelQuality().getLancet())
                .knittingType(creelDto.getCreelQuality().getKnittingType())
                .bottomWoofYarn(bottomWoofYarn)
                .middleWoofYarn(middleWoofYarn)
                .topWoofYarn(topWoofYarn)
                .build();

        //Palet Tanımı Ekleniyor
        CreelPalette palette = CreelPalette.builder()
                .versionNumber((short) 1)
                .creel(creel)
                .build();

        List<CreelPaletteLine> paletteLines = creelDto.getCreelPalette().getCreelPaletteLines().stream().map(m->{
            CreelPaletteLine line = CreelPaletteLine.builder()
                    .rotationNumber(m.getRotationNumber())
                    .color(colorService.getFromDto(m.getColor()))
                    .creelPalette(palette)
                    .build();
            List<CreelPaletteLineToYarn> lattes = m.getLineToYarns().stream().map(l-> CreelPaletteLineToYarn.builder()
                    .lattePercentage(l.getLattePercentage())
                    .creelPaletteLine(line)
                    .yarn(yarnService.getById(l.getYarn().getId()))
                    .build()).collect(Collectors.toList());

            line.setLineToYarns(lattes);
            return line;
        }).collect(Collectors.toList());

        palette.setCreelPaletteLines(paletteLines);

        //Açıklama Ekleniyor
        List<CreelToExplanation> exps = creelDto.getExplanations().stream().map(e-> CreelToExplanation.builder()
                .creel(creel)
                .explanation(explanationService.getExplanationEntity(e.getExplanation()))
                .build()
        ).collect(Collectors.toList());

        creel.setDesignPlan(designPlan);
        creel.setCreelQuality(quality);
        creel.setCreelPalette(palette);
        creel.setExplanations(exps);

        dao.save(creel);

        CreelDto dto = mapper.toDto(creel);
        dto.setDesignPlan(creelDto.getDesignPlan());
        CreelQualityDto qualityDto = mapper.toDto(creel.getCreelQuality());
        qualityDto.setBottomWoofYarn(creelDto.getCreelQuality().getBottomWoofYarn());
        qualityDto.setMiddleWoofYarn(creelDto.getCreelQuality().getMiddleWoofYarn());
        qualityDto.setTopWoofYarn(creelDto.getCreelQuality().getTopWoofYarn());

        CreelPaletteDto paletteDto = mapper.toDto(creel.getCreelPalette());
        paletteDto.setCreelPaletteLines(creel.getCreelPalette().getCreelPaletteLines().stream().map(l-> {
            CreelPaletteLineDto lineDto = mapper.toDto(l);
            lineDto.setLineToYarns(l.getLineToYarns().stream().map(mapper::toDto).collect(Collectors.toList()));
            return lineDto;
        }).collect(Collectors.toList()));

        dto.setCreelQuality(qualityDto);
        dto.setCreelPalette(paletteDto);
        dto.setExplanations(creel.getExplanations().stream()
                .map(ed-> CreelToExplanationDto.builder().explanation(explanationService.getExplanationDto(ed.getExplanation(), true)).build())
                .collect(Collectors.toList())
        );
        return dto;
    }

    @Override
    public void update(CreelDto creelDto) {
        Creel creel = dao.getById(creelDto.getId());
        DesignPlan designPlan = designPlanService.getById(creelDto.getDesignPlan().getId());
        creel.setPaletteCode(creelDto.getPaletteCode());
        creel.setQualityName(creelDto.getQualityName());
        creel.setSampleName(creelDto.getSampleName());
        creel.setRaising(creelDto.getRaising());
        creel.setWashing(creelDto.getWashing());
        creel.setDesignPlan(designPlan);
        dao.save(creel);
    }

    @Override
    public List<CreelDto> findAll() {
        return dao.findAll().stream().map(m-> {
            var dto = mapper.toDto(m);
            dto.setDesignPlan(designPlanMapper.toDto(m.getDesignPlan()));
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public CreelDto findOne(String id) {
        var creel = dao.getById(id);
        var dto = mapper.toDto(creel);
        dto.setDesignPlan(designPlanMapper.toDto(creel.getDesignPlan()));
        var creelPalette = mapper.toDto(creel.getCreelPalette());
        var lines = creel.getCreelPalette().getCreelPaletteLines().stream().map(l-> {
            var line = mapper.toDto(l);
            line.setColor(designMapper.toDto(l.getColor()));
            line.setLineToYarns(
                    l.getLineToYarns().stream().map(mapper::toDto).collect(Collectors.toList())
            );
            return line;
        }).collect(Collectors.toList());
        creelPalette.setCreelPaletteLines(lines);
        dto.setCreelPalette(creelPalette);
        var creelQuality = mapper.toDto(creel.getCreelQuality());
        creelQuality.setBottomWoofYarn(yarnMapper.toDto(creel.getCreelQuality().getBottomWoofYarn()));
        creelQuality.setMiddleWoofYarn(yarnMapper.toDto(creel.getCreelQuality().getMiddleWoofYarn()));
        creelQuality.setTopWoofYarn(yarnMapper.toDto(creel.getCreelQuality().getTopWoofYarn()));
        dto.setCreelQuality(creelQuality);
        return dto;
    }

    @Override
    public void delete(String id) {
        dao.deleteById(id);
    }

    @Override
    public void setCreelVersion(Creel creel, int qualityVersion, int paletteVersion) {
        String[] versions = creel.getActiveVersion().split("//.");
        String qv = qualityVersion == 0 ? versions[0] : String.valueOf(qualityVersion);
        String pv = paletteVersion == 0 ? versions[1] : String.valueOf(paletteVersion);

        creel.setActiveVersion(qv + "." + pv);
    }

    @Override
    public List<CreelDto> findByDesignPlanId(String designPlanId) {
        return dao.findByDesignPlanId(designPlanId).stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public Creel getById(String id) {
        return dao.getById(id);
    }

    @Override
    public CreelDto findByArchiveId(String archiveId) {
        Creel creel = dao.findByArchivesId(archiveId);
        CreelDto dto = mapper.toDto(creel);
        dto.setCreelQuality(mapper.toDto(creel.getCreelQuality()));
        dto.setCreelPalette(mapper.toDto(creel.getCreelPalette()));
        return dto;
    }
}