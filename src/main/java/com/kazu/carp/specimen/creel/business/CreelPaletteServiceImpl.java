package com.kazu.carp.specimen.creel.business;

import com.kazu.carp.specimen.creel.domain.CreelPalette;
import com.kazu.carp.specimen.creel.dto.CreelPaletteDto;
import com.kazu.carp.specimen.creel.dto.ICreelMapper;
import com.kazu.carp.specimen.creel.persistence.ICreelPaletteDao;
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
public class CreelPaletteServiceImpl implements ICreelPaletteService {
    private static final Logger logger = LoggerFactory.getLogger(CreelPaletteServiceImpl.class);

    final ICreelPaletteDao dao;
    final ICreelMapper mapper;

    public CreelPaletteServiceImpl(ICreelPaletteDao dao, ICreelMapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    @Override
    public CreelPalette getById(String id) {
        return dao.getById(id);
    }

    @Override
    public CreelPaletteDto findOne(String id) {
        return mapper.toDto(dao.getById(id));
    }

    @Override
    public List<CreelPaletteDto> findByCreelId(String creelId) {
        return dao.findByCreelId(creelId).stream().map(m->mapper.toDto(m)).collect(Collectors.toList());
    }
}