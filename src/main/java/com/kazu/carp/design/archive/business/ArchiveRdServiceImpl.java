package com.kazu.carp.design.archive.business;

import com.kazu.carp.common.file.userFile.business.IUserFileService;
import com.kazu.carp.common.file.util.business.IFileOperationService;
import com.kazu.carp.common.file.util.business.IFileUtilService;
import com.kazu.carp.common.file.util.dto.IUserFileMapper;
import com.kazu.carp.common.firm.business.IFirmService;
import com.kazu.carp.common.firm.dto.IFirmMapper;
import com.kazu.carp.common.util.business.IComplementaryService;
import com.kazu.carp.design.archive.domain.Archive;
import com.kazu.carp.design.archive.dto.ArchiveDto;
import com.kazu.carp.design.archive.dto.ArchiveStatus;
import com.kazu.carp.design.archive.dto.IArchiveMapper;
import com.kazu.carp.design.archive.persistence.IArchiveDao;
import com.kazu.carp.design.plan.business.IDesignPlanService;
import com.kazu.carp.design.plan.dto.IDesignPlanMapper;
import com.kazu.carp.image.business.IImageUtilService;
import com.kazu.carp.security.account.business.IUserAccountService;
import com.kazu.carp.security.account.dto.IUserAccountMapper;
import com.kazu.carp.specimen.creel.business.ICreelService;
import com.kazu.carp.specimen.creel.dto.ICreelMapper;
import com.kazu.carp.supplyChain.production.definition.stockCard.business.*;
import com.kazu.carp.supplyChain.production.definition.stockCard.dto.IStockCardDefinitionMapper;
import com.kazu.carp.yarn.definition.business.IYarnService;
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
 * 29.06.2021
 */
@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackForClassName = {"Exception"})
public class ArchiveRdServiceImpl extends BaseArchiveService implements IArchiveRdService {
    private static final Logger logger = LoggerFactory.getLogger(ArchiveRdServiceImpl.class);

    public ArchiveRdServiceImpl(IArchiveDao dao, ICreelService creelService, ICollectionService collectionService, IQualityService qualityService, IPaletteService paletteService, IPaletteLineToYarnService paletteLineLatteService, IDesignService designService, ISizeService sizeService, IShapeService shapeService, IEdgeService edgeService, IYarnService yarnService, IFirmService firmService, IDesignPlanService designPlanService, IUserAccountService userAccountService, IUserFileService userFileService, IFileUtilService fileUtilService, IFileOperationService fileOperationService, IComplementaryService complementaryService, IImageUtilService imageUtilService, IArchiveMapper mapper, ICreelMapper creelMapper, IStockCardDefinitionMapper stockCardDefinitionMapper, IYarnMapper yarnMapper, IFirmMapper firmMapper, IDesignPlanMapper designPlanMapper, IUserAccountMapper userAccountMapper, IUserFileMapper userFileMapper) {
        super(dao, creelService, collectionService, qualityService, paletteService, paletteLineLatteService, designService, sizeService, shapeService, edgeService, yarnService, firmService, designPlanService, userAccountService, userFileService, fileUtilService, fileOperationService, complementaryService, imageUtilService, mapper, creelMapper, stockCardDefinitionMapper, yarnMapper, firmMapper, designPlanMapper, userAccountMapper, userFileMapper);
    }

    @Override
    public List<ArchiveDto> findByPlanId(String planId) {
        List<Archive> archives = dao.findByDesignPlanIdAndArchiveStatus(planId, ArchiveStatus.RD);
        return archives.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    @Override
    public ArchiveDto findOne(String id) {
        return convertEntityToDto(dao.getById(id));
    }


}