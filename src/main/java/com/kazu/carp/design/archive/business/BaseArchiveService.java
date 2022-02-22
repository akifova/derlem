package com.kazu.carp.design.archive.business;

import com.kazu.carp.common.file.userFile.business.IUserFileService;
import com.kazu.carp.common.file.userFile.dto.UserFileDto;
import com.kazu.carp.common.file.util.business.IFileOperationService;
import com.kazu.carp.common.file.util.business.IFileUtilService;
import com.kazu.carp.common.file.util.dto.IUserFileMapper;
import com.kazu.carp.common.file.util.dto.ModuleFolder;
import com.kazu.carp.common.firm.business.IFirmService;
import com.kazu.carp.common.firm.domain.Firm;
import com.kazu.carp.common.firm.dto.IFirmMapper;
import com.kazu.carp.common.util.business.IComplementaryService;
import com.kazu.carp.design.archive.domain.Archive;
import com.kazu.carp.design.archive.domain.ArchiveToUserFile;
import com.kazu.carp.design.archive.domain.PixelRatio;
import com.kazu.carp.design.archive.dto.*;
import com.kazu.carp.design.archive.persistence.IArchiveDao;
import com.kazu.carp.design.plan.business.IDesignPlanService;
import com.kazu.carp.design.plan.domain.DesignPlan;
import com.kazu.carp.design.plan.dto.IDesignPlanMapper;
import com.kazu.carp.image.business.IImageUtilService;
import com.kazu.carp.security.account.business.IUserAccountService;
import com.kazu.carp.security.account.domain.UserAccount;
import com.kazu.carp.security.account.dto.IUserAccountMapper;
import com.kazu.carp.specimen.creel.business.ICreelService;
import com.kazu.carp.specimen.creel.domain.Creel;
import com.kazu.carp.specimen.creel.dto.ICreelMapper;
import com.kazu.carp.supplyChain.production.definition.stockCard.business.*;
import com.kazu.carp.supplyChain.production.definition.stockCard.domain.*;
import com.kazu.carp.supplyChain.production.definition.stockCard.dto.IStockCardDefinitionMapper;
import com.kazu.carp.yarn.definition.business.IYarnService;
import com.kazu.carp.yarn.definition.domain.Yarn;
import com.kazu.carp.yarn.definition.dto.IYarnMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author akifova
 * 07.07.2021
 */
public class BaseArchiveService {
    final IArchiveDao dao;
    final ICreelService creelService;
    final ICollectionService collectionService;
    final IQualityService qualityService;
    final IPaletteService paletteService;
    final IPaletteLineToYarnService paletteLineLatteService;
    final IDesignService designService;
    final ISizeService sizeService;
    final IShapeService shapeService;
    final IEdgeService edgeService;
    final IYarnService yarnService;
    final IFirmService firmService;
    final IDesignPlanService designPlanService;
    final IUserAccountService userAccountService;
    final IUserFileService userFileService;
    final IFileUtilService fileUtilService;
    final IFileOperationService fileOperationService;
    final IComplementaryService complementaryService;
    final IImageUtilService imageUtilService;
    final IArchiveMapper mapper;
    final ICreelMapper creelMapper;
    final IStockCardDefinitionMapper stockCardDefinitionMapper;
    final IYarnMapper yarnMapper;
    final IFirmMapper firmMapper;
    final IDesignPlanMapper designPlanMapper;
    final IUserAccountMapper userAccountMapper;
    final IUserFileMapper userFileMapper;


    public BaseArchiveService(IArchiveDao dao, ICreelService creelService, ICollectionService collectionService,
                              IQualityService qualityService, IPaletteService paletteService,
                              IPaletteLineToYarnService paletteLineLatteService, IDesignService designService,
                              ISizeService sizeService, IShapeService shapeService, IEdgeService edgeService,
                              IYarnService yarnService, IFirmService firmService,
                              IDesignPlanService designPlanService, IUserAccountService userAccountService,
                              IUserFileService userFileService, IFileUtilService fileUtilService,
                              IFileOperationService fileOperationService, IComplementaryService complementaryService,
                              IImageUtilService imageUtilService, IArchiveMapper mapper, ICreelMapper creelMapper,
                              IStockCardDefinitionMapper stockCardDefinitionMapper, IYarnMapper yarnMapper,
                              IFirmMapper firmMapper, IDesignPlanMapper designPlanMapper, IUserAccountMapper userAccountMapper,
                              IUserFileMapper userFileMapper) {
        this.dao = dao;
        this.creelService = creelService;
        this.collectionService = collectionService;
        this.qualityService = qualityService;
        this.paletteService = paletteService;
        this.paletteLineLatteService = paletteLineLatteService;
        this.designService = designService;
        this.sizeService = sizeService;
        this.shapeService = shapeService;
        this.edgeService = edgeService;
        this.yarnService = yarnService;
        this.firmService = firmService;
        this.designPlanService = designPlanService;
        this.userAccountService = userAccountService;
        this.userFileService = userFileService;
        this.fileUtilService = fileUtilService;
        this.fileOperationService = fileOperationService;
        this.complementaryService = complementaryService;
        this.imageUtilService = imageUtilService;
        this.mapper = mapper;
        this.creelMapper = creelMapper;
        this.stockCardDefinitionMapper = stockCardDefinitionMapper;
        this.yarnMapper = yarnMapper;
        this.firmMapper = firmMapper;
        this.designPlanMapper = designPlanMapper;
        this.userAccountMapper = userAccountMapper;
        this.userFileMapper = userFileMapper;
    }

    ArchiveDto convertEntityToDto(Archive archive) {
        ArchiveDto archiveDto = mapper.toDto(archive);
        if(archiveDto == null) { return null; }

        archiveDto.setCollection(stockCardDefinitionMapper.toDto(archive.getCollection()));
        archiveDto.setQuality(stockCardDefinitionMapper.toDto(archive.getQuality()));
        archiveDto.setPalette(stockCardDefinitionMapper.toDto(archive.getPalette()));
        archiveDto.setSize(stockCardDefinitionMapper.toDto(archive.getSize()));
        archiveDto.setDesign(stockCardDefinitionMapper.toDto(archive.getDesign()));
        archiveDto.setCreel(creelMapper.toDto(archive.getCreel()));
        archiveDto.setBordure(stockCardDefinitionMapper.toDto(archive.getBordure()));
        archiveDto.setFloor(stockCardDefinitionMapper.toDto(archive.getFloor()));
        archiveDto.setShape(stockCardDefinitionMapper.toDto(archive.getShape()));
        archiveDto.setEdge(stockCardDefinitionMapper.toDto(archive.getEdge()));
        archiveDto.setOverlockYarn(yarnMapper.toDto(archive.getOverlockYarn()));
        archiveDto.setSideEdge(stockCardDefinitionMapper.toDto(archive.getSideEdge()));
        archiveDto.setCustomer(firmMapper.toDto(archive.getCustomer()));
        archiveDto.setDesignPlan(designPlanMapper.toDto(archive.getDesignPlan()));
        archiveDto.setDesigner(userAccountMapper.toDto(archive.getDesigner()));

        archiveDto.setArchiveToUserFiles(
                archive.getArchiveToUserFiles().stream().map(f-> ArchiveToUserFileDto.builder()
                        .fileType(f.getFileType())
                        .versionNumber(f.getVersionNumber())
                        .versionDate(f.getVersionDate())
                        .id(f.getId())
                        .active(f.isActive())
                        .insertDate(f.getInsertDate())
                        .savesUser(f.getSavesUser())
                        .userFile(userFileMapper.toDto(f.getUserFile()))
                        .build()).collect(Collectors.toList())
        );

        return archiveDto;
    }

    void prepareBaseDataAndReturn(ArchiveDto archiveDto, Archive archive, ArchiveStatus newStatus, boolean setVersion) {
        Collection collection = archiveDto.getCollection() == null ? null : collectionService.getById(archiveDto.getCollection().getId());
        Quality quality = archiveDto.getQuality() == null ? null : qualityService.getById(archiveDto.getQuality().getId());
        Palette palette = archiveDto.getPalette() == null ? null : paletteService.getById(archiveDto.getPalette().getId());
        Size size = archiveDto.getSize() == null ? null : sizeService.getById(archiveDto.getSize().getId());
        Shape shape = archiveDto.getShape() == null ? null : shapeService.getById(archiveDto.getShape().getId());
        Edge edge = archiveDto.getEdge() == null ? null : edgeService.getById(archiveDto.getEdge().getId());
        Firm customer = archiveDto.getCustomer() == null ? null : firmService.getById(archiveDto.getCustomer().getId());
        UserAccount designer = archiveDto.getDesigner() == null ? null : userAccountService.getById(archiveDto.getDesigner().getId());

        Creel creel = creelService.getById(archiveDto.getCreel().getId());
        PaletteLineToYarn bordure = archiveDto.getBordure() == null ? null : paletteLineLatteService.getById(archiveDto.getBordure().getId());
        PaletteLineToYarn floor = archiveDto.getFloor() == null ? null : paletteLineLatteService.getById(archiveDto.getBordure().getId());
        Design designCode = archiveDto.getDesign() == null ? null : designService.getById(archiveDto.getDesign().getId());
        Yarn overlockYarn = archiveDto.getOverlockYarn() == null ? null : yarnService.getById(archiveDto.getOverlockYarn().getId());
        Edge sideEdge = archiveDto.getSideEdge() == null ? null : edgeService.getById(archiveDto.getSideEdge().getId());
        DesignPlan designPlan = archiveDto.getDesignPlan() == null ? null : designPlanService.getById(archiveDto.getDesignPlan().getId());

        archive.setCreel(creel);
        archive.setCollection(collection);
        archive.setQuality(quality);
        archive.setPalette(palette);
        archive.setBordure(bordure);
        archive.setFloor(floor);
        archive.setDesign(designCode);
        archive.setOverlockYarn(overlockYarn);
        archive.setSideEdge(sideEdge);
        archive.setDesignPlan(designPlan);
        archive.setDesignName(archiveDto.getDesignName());
        archive.setStarRating(archiveDto.getStarRating());
        archive.setDifficulty(archiveDto.getDifficulty());
        archive.setVersionDate(LocalDateTime.now());
        archive.setTag(archiveDto.getTag());
        archive.setArchiveStatus(ArchiveStatus.RD);
        archive.setWeaveType(archiveDto.getWeaveType());
        archive.setMakingRare(archiveDto.isMakingRare());
        archive.setMakingRarePaletteIndexes(archiveDto.getMakingRarePaletteIndexes());
        archive.setSize(size);
        archive.setShape(shape);
        archive.setEdge(edge);
        archive.setCustomer(customer);
        archive.setDesigner(designer);
        archive.setArchiveStatus(newStatus);

        short version = 1;
        if(setVersion) {
            List<Archive> archives = dao.findByQualityIdAndCollectionIdAndPaletteIdAndDesignIdAndSizeIdAndShapeIdAndEdgeId(
                    archive.getQuality().getId(), archive.getCollection().getId(), archive.getPalette().getId(),
                    archive.getDesign().getId(), archive.getSize().getId(), archive.getShape().getId(), archive.getEdge().getId());
            if(archives.size() > 0) {
                version = (short) (archives.stream().mapToInt(Archive::getVersionNumber).max().orElse(1) + 1);
            }
        }
        archive.setVersionNumber(version);
    }

    List<String> checkSampleAndArchiveSaveData(Archive archive) {
        List<String> nullFields = new ArrayList<>();
        if(archive.getCreel() == null || StringUtils.isEmpty(archive.getCreel().getId())) {
            nullFields.add("creel");
        }

        if(archive.getCollection() == null || StringUtils.isEmpty(archive.getCollection().getId())) {
            nullFields.add("collection");
        }

        if(archive.getQuality() == null || StringUtils.isEmpty(archive.getQuality().getId())) {
            nullFields.add("quality");
        }

        if(archive.getPalette() == null || StringUtils.isEmpty(archive.getPalette().getId())) {
            nullFields.add("palette");
        }

        if(archive.getDesign() == null || StringUtils.isEmpty(archive.getDesign().getId())) {
            nullFields.add("design");
        }

        if(archive.getSize() == null || StringUtils.isEmpty(archive.getSize().getId())) {
            nullFields.add("size");
        }

        if(archive.getShape() == null || StringUtils.isEmpty(archive.getShape().getId())) {
            nullFields.add("shape");
        }

        if(archive.getEdge() == null || StringUtils.isEmpty(archive.getEdge().getId())) {
            nullFields.add("edge");
        }

        /*if(archive.getOverlockYarn() == null || StringUtils.isEmpty(archive.getOverlockYarn().getId())) {
            nullFields.add("overlockYarn");
        }*/

        if(archive.getSideEdge() == null || StringUtils.isEmpty(archive.getSideEdge().getId())) {
            nullFields.add("sideEdge");
        }

        if(archive.getDesignPlan() == null || StringUtils.isEmpty(archive.getDesignPlan().getId())) {
            nullFields.add("designPlan");
        }

        if(archive.getDesigner() == null || StringUtils.isEmpty(archive.getDesigner().getId())) {
            nullFields.add("designer");
        }

        if(archive.getArchiveToUserFiles() != null) {
            var original = archive.getArchiveToUserFiles().stream()
                    .filter(f -> f.getFileType() == ArchiveFileType.original)
                    .map(m -> m.getFileType()).findFirst().orElse(null);

            var empty = archive.getArchiveToUserFiles().stream()
                    .filter(f -> f.getFileType() == ArchiveFileType.empty)
                    .map(m -> m.getFileType()).findFirst().orElse(null);

            var scanning = archive.getArchiveToUserFiles().stream()
                    .filter(f -> f.getFileType() == ArchiveFileType.scanning)
                    .map(m -> m.getFileType()).findFirst().orElse(null);

            if (original == null) {
                nullFields.add("originalDesignFile");
            }
            if (empty == null) {
                nullFields.add("emptyDesignFile");
            }
            if (scanning == null) {
                nullFields.add("scanningDesignFile");
            }
        }

        return nullFields;
    }

    void createFileEntitiesAndFileData(Archive archive, MultipartFile design, MultipartFile[] empties,
                                       MultipartFile[] scans, List<SaveArchiveFileDto> files) throws IOException {

        var location = fileUtilService.createLocation(ModuleFolder.DESIGN_ARCHIVE, Arrays.asList(complementaryService.createUniqueFromSecond()));
        List<ArchiveToUserFile> archiveToUserFiles = new ArrayList<>();
        ArchiveToUserFile designFile = ArchiveToUserFile.builder()
                .userFile(userFileService.createEntity(design, location))
                .archive(archive)
                .fileType(ArchiveFileType.original)
                .versionNumber(archive.getVersionNumber())
                .versionDate(LocalDateTime.now())
                .build();
        archiveToUserFiles.add(designFile);
        files.add(SaveArchiveFileDto.builder()
                .file(design.getBytes())
                .location(designFile.getUserFile().getLocation())
                .fileName(designFile.getUserFile().getFileName())
                .build());

        for (MultipartFile empty : empties) {
            ArchiveToUserFile emptyFile = ArchiveToUserFile.builder()
                    .userFile(userFileService.createEntity(empty, location))
                    .archive(archive)
                    .fileType(ArchiveFileType.empty)
                    .versionNumber(archive.getVersionNumber())
                    .versionDate(LocalDateTime.now())
                    .build();
            archiveToUserFiles.add(emptyFile);
            files.add(SaveArchiveFileDto.builder()
                    .file(empty.getBytes())
                    .location(emptyFile.getUserFile().getLocation())
                    .fileName(emptyFile.getUserFile().getFileName())
                    .build());
        }

        for (MultipartFile scanning : scans) {
            ArchiveToUserFile scanningFile = ArchiveToUserFile.builder()
                    .userFile(userFileService.createEntity(scanning, location))
                    .archive(archive)
                    .fileType(ArchiveFileType.scanning)
                    .versionNumber(archive.getVersionNumber())
                    .versionDate(LocalDateTime.now())
                    .build();
            archiveToUserFiles.add(scanningFile);
            files.add(SaveArchiveFileDto.builder()
                    .file(scanning.getBytes())
                    .location(scanningFile.getUserFile().getLocation())
                    .fileName(scanningFile.getUserFile().getFileName())
                    .build());
        }

        archive.setArchiveToUserFiles(archiveToUserFiles);

        //Pixel Ratios

        var imageRatios = imageUtilService.getColorPalette(design.getBytes(), true);
        List<PixelRatio> ratios = imageRatios.stream().map(m-> PixelRatio.builder()
                .archive(archive)
                .versionNumber(archive.getVersionNumber())
                .versionDate(LocalDateTime.now())
                .paletteNo(m.getPaletteNo())
                .count(m.getCount())
                .rgbRed(m.getRed())
                .rgbGreen(m.getGreen())
                .rgbBlue(m.getBlue())
                .ratio(m.getRatio())
                .build()).collect(Collectors.toList());
        archive.setPixelRatios(ratios);
    }

    ArchiveDto mapArchiveAndFilesToDto(Archive archive) {
        var dto = mapper.toDto(archive);
        dto.setArchiveToUserFiles(new ArrayList<>());

        for (ArchiveToUserFile atuf: archive.getArchiveToUserFiles()) {
            UserFileDto fileDto = UserFileDto.builder()
                    .fileName(atuf.getUserFile().getFileName())
                    .originalFileName(atuf.getUserFile().getOriginalFileName())
                    .location(atuf.getUserFile().getLocation())
                    .url(atuf.getUserFile().getUrl())
                    .fileType(atuf.getUserFile().getFileType())
                    .fileSize(atuf.getUserFile().getFileSize())
                    .build();
            dto.getArchiveToUserFiles().add(ArchiveToUserFileDto.builder()
                    .versionNumber(archive.getVersionNumber())
                    .versionDate(LocalDateTime.now())
                    .userFile(fileDto)
                    .fileType(atuf.getFileType())
                    .build());
        }

        return dto;
    }
}
