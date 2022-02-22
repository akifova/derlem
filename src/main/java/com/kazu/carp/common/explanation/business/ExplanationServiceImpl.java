package com.kazu.carp.common.explanation.business;

import com.kazu.carp.common.explanation.domain.Explanation;
import com.kazu.carp.common.explanation.dto.ExplanationDto;
import com.kazu.carp.common.explanation.dto.IExplanationMapper;
import com.kazu.carp.common.explanation.persistence.IExplanationDao;
import com.kazu.carp.security.account.business.ICarpActiveUserService;
import com.kazu.carp.security.account.dto.IUserAccountMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.Charset;
import java.util.stream.Collectors;

/**
 * @author akifova
 * 14.02.2021
 */
@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackForClassName = {"Exception"})
public class ExplanationServiceImpl implements IExplanationService {
    private static final Logger logger = LoggerFactory.getLogger(ExplanationServiceImpl.class);

    final IExplanationDao dao;
    final ICarpActiveUserService activeUserService;
    final IExplanationMapper mapper;
    final IUserAccountMapper userMapper;

    public ExplanationServiceImpl(IExplanationDao dao, ICarpActiveUserService activeUserService,
                                  IExplanationMapper mapper, IUserAccountMapper userMapper) {
        this.dao = dao;
        this.activeUserService = activeUserService;
        this.mapper = mapper;
        this.userMapper = userMapper;
    }

    @Override
    public Explanation getExplanationEntity(ExplanationDto explanationDto) {
        Explanation explanation;
        if (StringUtils.isEmpty(explanationDto.getId())){
            explanation = new Explanation();
            explanation.setBackColor("FFFFFF");
            explanation.setLikeCount((short) 0);
            explanation.setWriter(activeUserService.findActiveUserAccount());
        }else{
            explanation = dao.getById(explanationDto.getId());
        }

        explanation.setText(explanationDto.getText().getBytes(Charset.forName("UTF-8")));
        return explanation;
    }

    @Override
    public ExplanationDto getExplanationDto(Explanation explanation, boolean includeComments) {
        ExplanationDto dto = mapper.map(explanation);
        dto.setWriter(userMapper.toDto(explanation.getWriter()));
        if(includeComments && explanation.getComments() != null) {
            dto.setComments(explanation.getComments().stream().map(c-> {
                ExplanationDto exDto = mapper.map(c);
                exDto.setWriter(userMapper.toDto(c.getWriter()));
                return exDto;
            }).collect(Collectors.toList()));
        }
        return dto;
    }
}