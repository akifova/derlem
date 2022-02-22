package com.kazu.carp.design.archive.business;

import com.kazu.carp.app.exception.design.OnlyProductsCanBeRetiredException;
import com.kazu.carp.common.file.userFile.business.IUserFileService;
import com.kazu.carp.common.file.util.business.IFileOperationService;
import com.kazu.carp.common.file.util.business.IFileUtilService;
import com.kazu.carp.common.file.util.dto.IUserFileMapper;
import com.kazu.carp.common.firm.business.IFirmService;
import com.kazu.carp.common.firm.dto.IFirmMapper;
import com.kazu.carp.common.util.business.IComplementaryService;
import com.kazu.carp.design.archive.domain.Archive;
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

/**
 * @author akifova
 * 17.07.2021
 */
@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackForClassName = {"Exception"})
public class ArchiveShelveServiceImpl extends BaseArchiveService implements IArchiveShelveService {
    private static final Logger logger = LoggerFactory.getLogger(ArchiveShelveServiceImpl.class);

    public ArchiveShelveServiceImpl(IArchiveDao dao, ICreelService creelService, ICollectionService collectionService, IQualityService qualityService, IPaletteService paletteService, IPaletteLineToYarnService paletteLineLatteService, IDesignService designService, ISizeService sizeService, IShapeService shapeService, IEdgeService edgeService, IYarnService yarnService, IFirmService firmService, IDesignPlanService designPlanService, IUserAccountService userAccountService, IUserFileService userFileService, IFileUtilService fileUtilService, IFileOperationService fileOperationService, IComplementaryService complementaryService, IImageUtilService imageUtilService, IArchiveMapper mapper, ICreelMapper creelMapper, IStockCardDefinitionMapper stockCardDefinitionMapper, IYarnMapper yarnMapper, IFirmMapper firmMapper, IDesignPlanMapper designPlanMapper, IUserAccountMapper userAccountMapper, IUserFileMapper userFileMapper) {
        super(dao, creelService, collectionService, qualityService, paletteService, paletteLineLatteService, designService, sizeService, shapeService, edgeService, yarnService, firmService, designPlanService, userAccountService, userFileService, fileUtilService, fileOperationService, complementaryService, imageUtilService, mapper, creelMapper, stockCardDefinitionMapper, yarnMapper, firmMapper, designPlanMapper, userAccountMapper, userFileMapper);
    }

    @Override
    public void convertToStock(String archiveId) {
        Archive archive = dao.getById(archiveId);
        archive.setArchiveStatus(ArchiveStatus.STOCK);
        dao.save(archive);
    }

    @Override
    public void convertToUnusable(String archiveId) {
        Archive archive = dao.getById(archiveId);
        archive.setArchiveStatus(ArchiveStatus.UNUSABLE);
        dao.save(archive);
    }

    @Override
    public void convertToRetired(String archiveId) throws OnlyProductsCanBeRetiredException {
        Archive archive = dao.getById(archiveId);

        if(archive.getArchiveStatus() != ArchiveStatus.PRODUCT) {
            throw new OnlyProductsCanBeRetiredException();
        }

        archive.setArchiveStatus(ArchiveStatus.RETIRED);
        dao.save(archive);
    }
}