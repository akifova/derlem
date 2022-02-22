package com.kazu.carp.supplyChain.production.definition.stockCard.business;

import com.kazu.carp.app.jpa.specification.SpecCreator;
import com.kazu.carp.design.color.business.IColorService;
import com.kazu.carp.design.color.domain.Color;
import com.kazu.carp.supplyChain.production.definition.stockCard.domain.Palette;
import com.kazu.carp.supplyChain.production.definition.stockCard.domain.PaletteLine;
import com.kazu.carp.supplyChain.production.definition.stockCard.domain.PaletteLineToYarn;
import com.kazu.carp.supplyChain.production.definition.stockCard.dto.IStockCardDefinitionMapper;
import com.kazu.carp.supplyChain.production.definition.stockCard.dto.PaletteDto;
import com.kazu.carp.supplyChain.production.definition.stockCard.dto.PaletteFindSimilarDto;
import com.kazu.carp.supplyChain.production.definition.stockCard.persistence.IPaletteDao;
import com.kazu.carp.yarn.definition.business.IYarnService;
import com.kazu.carp.yarn.definition.dto.IYarnMapper;
import com.kazu.carp.yarn.definition.dto.YarnDto;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author akifova
 * 07.02.2021
 */
@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackForClassName = {"Exception"})
public class PaletteServiceImpl implements IPaletteService {
    private static final Logger logger = LoggerFactory.getLogger(PaletteServiceImpl.class);

    final IPaletteDao dao;
    final IStockCardDefinitionMapper mapper;
    final IColorService colorService;
    final IYarnService yarnService;
    final IYarnMapper yarnMapper;

    public PaletteServiceImpl(IPaletteDao dao, IStockCardDefinitionMapper mapper, IColorService colorService, IYarnService yarnService, IYarnMapper yarnMapper) {
        this.dao = dao;
        this.mapper = mapper;
        this.colorService = colorService;
        this.yarnService = yarnService;
        this.yarnMapper = yarnMapper;
    }

    @Override
    public PaletteDto create(PaletteDto paletteDto) {
        Palette palette = mapper.toEntity(paletteDto);
        var colors = paletteDto.getPaletteLines().stream()
                .map(m-> colorService.getFromDto(m.getColor()))
                .filter(distinctByKeys(Color::getRgbRed, Color::getRgbGreen, Color::getRgbBlue))
                .collect(Collectors.toList());

        palette.setPaletteLines(paletteDto.getPaletteLines().stream().map(m -> {
            PaletteLine line = mapper.toEntity(m);
            line.setPalette(palette);
            line.setColor(colors.stream()
                    .filter(f->
                            f.getRgbRed().equals(m.getColor().getRgbRed()) &&
                            f.getRgbGreen().equals(m.getColor().getRgbGreen()) &&
                            f.getRgbBlue().equals(m.getColor().getRgbBlue()))
                    .findFirst().get());
            line.setLineToYarns(
                    m.getLineToYarns().stream().map(l -> {
                        PaletteLineToYarn latte = mapper.toEntity(l);
                        latte.setPaletteLine(line);
                        latte.setYarn(yarnService.getById(l.getYarn().getId()));
                        return latte;
                    }).collect(Collectors.toList()));
            return line;
        }).collect(Collectors.toList()));
        dao.save(palette);
        return mapper.toDto(palette);
    }

    @Override
    public void update(PaletteDto paletteDto) {
        Palette palette = dao.getById(paletteDto.getId());
        palette.setCode(paletteDto.getCode());
        dao.save(palette);
    }

    @Override
    public List<PaletteDto> findAll() {
        return dao.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public PaletteDto findOne(String id) {
        return mapper.toDto(dao.getById(id));
    }

    @Override
    public PaletteDto findFirstByCode(String code) {
        return dao.findFirstByCode(code);
    }

    @Override
    public void delete(String id) {
        dao.deleteById(id);
    }

    @Override
    public Palette getById(String id) {
        if(StringUtils.isEmpty(id)) {
            return null;
        }
        return dao.getById(id);
    }

    @Override
    public List<PaletteDto> search(String sp) {
        var spec = new SpecCreator<Palette>(sp).getSpec();
        return dao.findAll(spec).stream().map(m-> mapper.toDto(m)).collect(Collectors.toList());
    }

    @Override
    public List<YarnDto> getYarns(String id) {
        Palette palette = dao.getById(id);
        List<YarnDto> yarns = new ArrayList<>();

        palette.getPaletteLines().stream().forEach(l->{
            l.getLineToYarns().stream().forEach(y-> {
                if(y.getYarn() != null && !yarns.stream().anyMatch(m->m.getId().equals(y.getYarn().getId()))) {
                    yarns.add(yarnMapper.toDto(y.getYarn()));
                }
            });
        });

        return yarns;
    }

    @Override
    public List<PaletteFindSimilarDto> findSimilar(List<String> colorCodes) {
        List<PaletteFindSimilarDto> similarOnes = dao.findSimilar(colorCodes);
        similarOnes.stream().forEach(f-> {
            f.setSimilarityRatio(new BigDecimal(f.getMatchColorCount())
                    .divide(new BigDecimal(colorCodes.size()), 2, RoundingMode.HALF_UP)
                    .multiply(new BigDecimal(100)));
        });

        return similarOnes;
    }

    private static <T> Predicate<T> distinctByKeys(Function<? super T, ?>... keyExtractors)
    {
        final Map<List<?>, Boolean> seen = new ConcurrentHashMap<>();

        return t ->
        {
            final List<?> keys = Arrays.stream(keyExtractors)
                    .map(ke -> ke.apply(t))
                    .collect(Collectors.toList());

            return seen.putIfAbsent(keys, Boolean.TRUE) == null;
        };
    }
}