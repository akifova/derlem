package com.kazu.carp.security.role.business;

import com.kazu.carp.security.account.dto.IUserAccountMapper;
import com.kazu.carp.security.role.domain.UserRole;
import com.kazu.carp.security.role.dto.RoleDto;
import com.kazu.carp.security.role.persistence.IRoleDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author akifova
 * @since 16.01.2021
 */
@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackForClassName = {"Exception"})
public class RoleServiceImpl implements IRoleService {
    private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

    final IRoleDao dao;
    final IUserAccountMapper mapper;

    public RoleServiceImpl(IRoleDao dao, IUserAccountMapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    @Override
    public UserRole getById(String id) {
        return dao.getById(id);
    }

    @Override
    public RoleDto create(RoleDto roleDto) {
        UserRole role = UserRole.builder()
                .authority(roleDto.getAuthority())
                .langParam(roleDto.getLangParam())
                .moduleLangParam(roleDto.getModuleLangParam())
                .formLangParam(roleDto.getFormLangParam())
                .description(roleDto.getDescription())
                .build();
        dao.save(role);
        return mapper.toDto(role);
    }

    @Override
    public void update(RoleDto roleDto) {
        UserRole role = dao.getById(roleDto.getId());
        role.setAuthority(roleDto.getAuthority());
        role.setLangParam(roleDto.getLangParam());
        role.setModuleLangParam(roleDto.getModuleLangParam());
        role.setFormLangParam(roleDto.getFormLangParam());
        role.setDescription(roleDto.getDescription());
        dao.save(role);
    }

    @Override
    public List<RoleDto> findAll() {
        return dao.findAll().stream().filter(f-> !f.getAuthority().equals("KAZU")).map(m-> mapper.toDto(m)).collect(Collectors.toList());
    }

    @Override
    public RoleDto findOne(String id) {
        var role = mapper.toDto(dao.getById(id));
        return role == null || role.getAuthority().equals("KAZU") ? null : role;
    }

    @Override
    public void remove(String id) {
        dao.deleteById(id);
    }
}