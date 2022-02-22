package com.kazu.carp.design.archive.persistence;

import com.kazu.carp.design.archive.domain.Archive;
import com.kazu.carp.design.archive.dto.ArchiveStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author akifova
 * 29.06.2021
 */
public interface IArchiveDao extends JpaRepository<Archive, String>, JpaSpecificationExecutor<Archive> {

    List<Archive> findByDesignPlanIdAndArchiveStatus(String planId, ArchiveStatus rd);

    List<Archive> findByQualityIdAndCollectionIdAndPaletteIdAndDesignIdAndSizeIdAndShapeIdAndEdgeId(String qualityId, String collectionId, String paletteId, String designCodeId, String sizeId, String shapeId, String edgeId);
}
