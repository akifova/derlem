package com.kazu.carp.security.role.presentation;

import com.kazu.carp.app.validation.common.OnUpdate;
import com.kazu.carp.security.role.business.IRoleService;
import com.kazu.carp.security.role.dto.RoleDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.accepted;

/**
 * @author akifova
 * @since 24.01.2021
 */
@RestController
@RequestMapping("/user/role")
@Tag(name = "Kullanıcı Rolleri", description = "Sadece Uygulama Yöneticisi Erişebilir")
public class RoleController {
    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

    final IRoleService service;

    public RoleController(IRoleService service) {
        this.service = service;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    public ResponseEntity<RoleDto> create(@Valid @RequestBody RoleDto roleDto) {
        RoleDto dto = service.create(roleDto);
        return new ResponseEntity<>(dto, CREATED);
    }

    @ResponseStatus(ACCEPTED)
    @PutMapping("/")
    public ResponseEntity update(@Validated(OnUpdate.class) @RequestBody RoleDto roleDto) {
        service.update(roleDto);
        return accepted().build();
    }

    @GetMapping("/get/{id}")
    public RoleDto findOne(@PathVariable String id) {
        return service.findOne(id);
    }

    @GetMapping("/get/")
    public List<RoleDto> findAll() {
        return service.findAll();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public ResponseEntity remove(@PathVariable String id) {
        service.remove(id);
        return ResponseEntity.noContent().build();
    }
}