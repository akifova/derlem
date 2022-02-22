package com.kazu.carp.security.authorization.presentation;

import com.kazu.carp.security.authorization.business.IAuthorizationService;
import com.kazu.carp.security.authorization.dto.AuthorizationDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author akifova
 * @since 22.01.2021
 */
@RestController
@RequestMapping("/user/authorization")
@Tag(name = "Kullanıcı Yetkileri", description = "")
public class AuthorizationController {
    private static final Logger logger = LoggerFactory.getLogger(AuthorizationController.class);

    final IAuthorizationService service;

    public AuthorizationController(IAuthorizationService service) {
        this.service = service;
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/{userId}/{roleId}")
    public ResponseEntity addRoleToUser(@PathVariable(value = "userId") String userId, @PathVariable(value = "roleId") String roleId) {
        AuthorizationDto dto = service.addRoleToUser(userId, roleId);
        return new ResponseEntity<AuthorizationDto>(dto, HttpStatus.ACCEPTED);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public ResponseEntity removeAuthorization(@PathVariable String id) {
        service.removeAuthorization(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{userId}")
    public List<AuthorizationDto> findByUserId(@PathVariable String userId) {
        return service.findByUserAccountId(userId);
    }
}