package com.kazu.carp.design.archive.business;

import com.kazu.carp.app.exception.common.FieldsCanNotBeNullException;
import com.kazu.carp.app.jpa.specification.SpecCreator;
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
import com.kazu.carp.design.archive.dto.SaveArchiveFileDto;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author akifova
 * 09.07.2021
 */
@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackForClassName = {"Exception"})
public class ArchiveServiceImpl extends BaseArchiveService implements IArchiveService {

    public ArchiveServiceImpl(IArchiveDao dao, ICreelService creelService, ICollectionService collectionService, IQualityService qualityService, IPaletteService paletteService, IPaletteLineToYarnService paletteLineLatteService, IDesignService designService, ISizeService sizeService, IShapeService shapeService, IEdgeService edgeService, IYarnService yarnService, IFirmService firmService, IDesignPlanService designPlanService, IUserAccountService userAccountService, IUserFileService userFileService, IFileUtilService fileUtilService, IFileOperationService fileOperationService, IComplementaryService complementaryService, IImageUtilService imageUtilService, IArchiveMapper mapper, ICreelMapper creelMapper, IStockCardDefinitionMapper stockCardDefinitionMapper, IYarnMapper yarnMapper, IFirmMapper firmMapper, IDesignPlanMapper designPlanMapper, IUserAccountMapper userAccountMapper, IUserFileMapper userFileMapper) {
        super(dao, creelService, collectionService, qualityService, paletteService, paletteLineLatteService, designService, sizeService, shapeService, edgeService, yarnService, firmService, designPlanService, userAccountService, userFileService, fileUtilService, fileOperationService, complementaryService, imageUtilService, mapper, creelMapper, stockCardDefinitionMapper, yarnMapper, firmMapper, designPlanMapper, userAccountMapper, userFileMapper);
    }

    @Override
    public void convertToArchive(String sampleId) throws FieldsCanNotBeNullException {
        Archive archive = dao.getById(sampleId);
        List<String> nullFields = checkSampleAndArchiveSaveData(archive);

        if(nullFields.size() > 0) {
            archive = null;
            throw new FieldsCanNotBeNullException(nullFields);
        }

        archive.setArchiveStatus(ArchiveStatus.PRODUCT);
    }

    @Override
    public ArchiveDto findOne(String id) {
        return convertEntityToDto(dao.getById(id));
    }

    @Override
    public List<ArchiveDto> search(String sp) {
        var spec = new SpecCreator<Archive>(sp).getSpec();
        List<Archive> archives = dao.findAll(spec);
        return archives.stream()
                .map(m-> { return convertEntityToDto(m); })
                .collect(Collectors.toList());
    }

    @Override
    public ArchiveDto addDesign(MultipartFile design, MultipartFile[] empties, MultipartFile[] scans,
                                ArchiveDto archiveDto, ArchiveStatus newStatus, boolean setVersion) throws IOException, FieldsCanNotBeNullException {
        Archive archive = new Archive();

        prepareBaseDataAndReturn(archiveDto, archive, newStatus, setVersion);

        if(newStatus != ArchiveStatus.RD) { //RD haricinde eklenenlerde Tüm fieldlar dolu olmalı.
            List<String> nullFields = checkSampleAndArchiveSaveData(archive);

            if (nullFields.size() > 0) {
                archive = null;
                throw new FieldsCanNotBeNullException(nullFields);
            }
        }
        List<SaveArchiveFileDto> files = new ArrayList<>();
        createFileEntitiesAndFileData(archive, design, empties, scans, files);

        dao.saveAndFlush(archive);

        files.stream().forEach(file ->{
            fileOperationService.saveImageFileOnDisk(file.getFile(), file.getLocation(), file.getFileName());
        });

        return mapArchiveAndFilesToDto(archive);
    }
}