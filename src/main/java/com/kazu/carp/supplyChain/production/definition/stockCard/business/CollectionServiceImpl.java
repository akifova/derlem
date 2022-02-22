package com.kazu.carp.supplyChain.production.definition.stockCard.business;

import com.kazu.carp.app.jpa.specification.SpecCreator;
import com.kazu.carp.common.brand.business.IBrandService;
import com.kazu.carp.common.brand.domain.Brand;
import com.kazu.carp.common.brand.dto.IBrandMapper;
import com.kazu.carp.supplyChain.production.definition.stockCard.domain.Collection;
import com.kazu.carp.supplyChain.production.definition.stockCard.dto.CollectionDto;
import com.kazu.carp.supplyChain.production.definition.stockCard.dto.IStockCardDefinitionMapper;
import com.kazu.carp.supplyChain.production.definition.stockCard.persistence.ICollectionDao;
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
 * 06.02.2021
 */
@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackForClassName = {"Exception"})
public class CollectionServiceImpl implements ICollectionService {
    private static final Logger logger = LoggerFactory.getLogger(CollectionServiceImpl.class);

    final ICollectionDao dao;
    final IStockCardDefinitionMapper mapper;
    final IBrandMapper brandMapper;
    final IBrandService brandService;

    public CollectionServiceImpl(ICollectionDao dao, IStockCardDefinitionMapper mapper, IBrandMapper brandMapper, IBrandService brandService) {
        this.dao = dao;
        this.mapper = mapper;
        this.brandMapper = brandMapper;
        this.brandService = brandService;
    }

    @Override
    public CollectionDto create(CollectionDto collectionDto) {
        Collection collection = mapper.toEntity(collectionDto);
        Brand brand = brandService.getById(collectionDto.getBrand().getId());
        collection.setBrand(brand);
        dao.save(collection);
        return mapper.toDto(collection);
    }

    @Override
    public void update(CollectionDto collectionDto) {
        Brand brand = brandService.getById(collectionDto.getBrand().getId());
        Collection collection = dao.getById(collectionDto.getId());
        collection.setCode(collectionDto.getCode());
        collection.setPainted(collectionDto.isPainted());
        collection.setShrinkage(collectionDto.getShrinkage());
        collection.setWashed(collectionDto.isWashed());
        collection.setHorizontalWeaving(collectionDto.isHorizontalWeaving());
        collection.setDefinition(collectionDto.getDefinition());
        collection.setBrand(brand);

        dao.save(collection);
    }

    @Override
    public List<CollectionDto> findAll() {
        return dao.findAll().stream().map(m-> {
            CollectionDto dto = mapper.toDto(m);
            dto.setBrand(brandMapper.toDto(m.getBrand()));
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public CollectionDto findOne(String id) {
        Collection collection = dao.getById(id);
        CollectionDto dto = mapper.toDto(collection);
        dto.setBrand(brandMapper.toDto(collection.getBrand()));
        return dto;
    }

    @Override
    public CollectionDto findByCode(String code) {
        return dao.findFirstByCode(code);
    }

    @Override
    public CollectionDto findByDefinition(String definition) {
        return dao.findFirstByDefinition(definition);
    }

    @Override
    public void remove(String id) {
        dao.delete(dao.getById(id));
    }

    @Override
    public List<CollectionDto> search(String sp) {
        var spec = new SpecCreator<Collection>(sp).getSpec();
        return dao.findAll(spec).stream().map(m-> {
            var dto = mapper.toDto(m);
            dto.setBrand(brandMapper.toDto(m.getBrand()));
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public Collection getById(String id) {
        if(StringUtils.isEmpty(id)) {
            return null;
        }

        return dao.getById(id);
    }
}