package com.kazu.carp.supplyChain.production.definition.stockCard.business;

import com.kazu.carp.supplyChain.production.definition.stockCard.domain.Quality;
import com.kazu.carp.supplyChain.production.definition.stockCard.dto.QualityDto;

import java.util.List;

/**
 * @author akifova
 * 06.02.2021
 */
public interface IQualityService {
    QualityDto create(QualityDto qualityDto);

    void update(QualityDto qualityDto);

    List<QualityDto> findAll();

    QualityDto findOne(String qualityId);

    QualityDto findFirstByCode(String code);

    void delete(String id);

    void deleteByCode(String code);

    List<QualityDto> search(String sp);

    Quality getById(String id);
}
