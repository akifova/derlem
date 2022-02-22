package com.kazu.carp.design.plan.business;

import com.kazu.carp.app.jpa.specification.SpecCreator;
import com.kazu.carp.common.explanation.business.IExplanationService;
import com.kazu.carp.common.explanation.dto.IExplanationMapper;
import com.kazu.carp.common.file.userFile.business.IUserFileService;
import com.kazu.carp.common.file.util.business.IFileOperationService;
import com.kazu.carp.common.file.util.business.IFileUtilService;
import com.kazu.carp.common.file.util.dto.IUserFileMapper;
import com.kazu.carp.common.file.util.dto.ModuleFolder;
import com.kazu.carp.common.util.business.IComplementaryService;
import com.kazu.carp.design.plan.domain.DesignPlan;
import com.kazu.carp.design.plan.domain.DesignPlanToUserFile;
import com.kazu.carp.design.plan.dto.DesignPlanDto;
import com.kazu.carp.design.plan.dto.DesignPlanToUserFileDto;
import com.kazu.carp.design.plan.dto.IDesignPlanMapper;
import com.kazu.carp.design.plan.persistence.IDesignPlanDao;
import com.kazu.carp.security.account.business.ICarpActiveUserService;
import com.kazu.carp.security.account.dto.IUserAccountMapper;
import com.kazu.carp.specimen.creel.dto.ICreelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author akifova
 * 14.02.2021
 */
@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackForClassName = {"Exception"})
public class DesignPlanServiceImpl implements IDesignPlanService {
    private static final Logger logger = LoggerFactory.getLogger(DesignPlanServiceImpl.class);

    final IDesignPlanDao dao;
    final IDesignPlanMapper mapper;
    final IUserFileService userFileService;
    final IComplementaryService complementaryService;
    final IFileUtilService fileUtilService;
    final IExplanationService explanationService;
    final ICarpActiveUserService activeUserService;
    final IFileOperationService fileOperationService;
    final IUserAccountMapper userAccountMapper;
    final IExplanationMapper explanationMapper;
    final IUserFileMapper userFileMapper;
    final ICreelMapper creelMapper;

    public DesignPlanServiceImpl(IDesignPlanDao dao, IDesignPlanMapper mapper, IUserFileService userFileService,
                                 IComplementaryService complementaryService, IFileUtilService fileUtilService,
                                 IExplanationService explanationService, ICarpActiveUserService activeUserService,
                                 IFileOperationService fileOperationService, IUserAccountMapper userAccountMapper, IExplanationMapper explanationMapper, IUserFileMapper userFileMapper, ICreelMapper creelMapper) {
        this.dao = dao;
        this.mapper = mapper;
        this.userFileService = userFileService;
        this.complementaryService = complementaryService;
        this.fileUtilService = fileUtilService;
        this.explanationService = explanationService;
        this.activeUserService = activeUserService;
        this.fileOperationService = fileOperationService;
        this.userAccountMapper = userAccountMapper;
        this.explanationMapper = explanationMapper;
        this.userFileMapper = userFileMapper;
        this.creelMapper = creelMapper;
    }

    @Override
    public DesignPlanDto create(@Valid DesignPlanDto designPlanDto, List<MultipartFile> files) {
        DesignPlan plan = mapper.toEntity(designPlanDto);

        var location = fileUtilService.createLocation(ModuleFolder.DESIGN_PLAN, Arrays.asList(complementaryService.createUniqueFromSecond()));
        List<DesignPlanToUserFile> userFiles = files.stream().map(m->
                DesignPlanToUserFile.builder()
                .designPlan(plan)
                .userFile(userFileService.createEntity(m, location))
                .build()).collect(Collectors.toList());

        plan.setDesignPlanToFiles(userFiles);
        plan.setOwner(activeUserService.findActiveUserAccount());
        plan.setTask(explanationService.getExplanationEntity(designPlanDto.getTask()));
        plan.setActive(true);
        dao.saveAndFlush(plan);

        files.stream().forEach(f-> {
            try {
                var fileEntity = userFiles.stream().map(m-> m.getUserFile()).filter(fl-> fl.getOriginalFileName().equals(f.getOriginalFilename())).findFirst().get();
                fileOperationService.saveImageFileOnDisk(f.getBytes(), fileEntity.getLocation(), fileEntity.getFileName());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        /*userFiles.stream().map(DesignPlanToUserFile::getUserFile).forEach(f-> {
            fileOperationService.saveImageFileOnDisk(f.getFileData().getFileData(), f.getLocation(), f.getFileName());
            f.setFileData(null);
        });*/
        return mapper.toDto(plan);
    }

    @Override
    public void update(DesignPlanDto designPlanDto) {
        DesignPlan designPlan = dao.getById(designPlanDto.getId());
        designPlan.setTitle(designPlanDto.getTitle());
        designPlan.setDeadLine(designPlanDto.getDeadLine());
        dao.save(designPlan);
    }

    @Override
    public List<DesignPlanDto> findAll() {
        return dao.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public DesignPlanDto findOne(String id) {
        DesignPlan dp = dao.getById(id);
        DesignPlanDto dto = mapper.toDto(dp);
        dto.setOwner(userAccountMapper.toDto(dp.getOwner()));
        dto.setTask(explanationMapper.map(dp.getTask()));
        dto.setDesignPlanToFiles(
                dp.getDesignPlanToFiles().stream().map(m-> {
                    DesignPlanToUserFileDto dpusf = new DesignPlanToUserFileDto();
                    dpusf.setUserFile(userFileMapper.toDto(m.getUserFile()));
                    return dpusf;
                }).collect(Collectors.toList())
        );
        return dto;
    }

    @Override
    public DesignPlan getById(String id) {
        return dao.getById(id);
    }

    @Override
    public List<DesignPlanDto> findActives() {
        return dao.findByActive(true).stream().map(m-> {
            var dto = mapper.toDto(m);
            dto.setCreels(m.getCreels().stream().map(creelMapper::toDto).collect(Collectors.toList()));
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public List<DesignPlanDto> search(String sp) {
        var spec = new SpecCreator<DesignPlan>(sp).getSpec();
        return dao.findAll(spec).stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void close(String planId) {
        DesignPlan plan = dao.getById(planId);
        plan.setActive(false);
    }
}