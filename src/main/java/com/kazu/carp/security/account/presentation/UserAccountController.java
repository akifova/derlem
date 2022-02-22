package com.kazu.carp.security.account.presentation;

import com.kazu.carp.app.validation.common.OnUpdate;
import com.kazu.carp.security.account.business.IUserAccountService;
import com.kazu.carp.security.account.dto.ChangePasswordDto;
import com.kazu.carp.security.account.dto.ResetPasswordDto;
import com.kazu.carp.security.account.dto.UserAccountDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author akifova
 * @since 16.01.2021
 */
@RestController
@RequestMapping("/user/account")
@Tag(name = "Kullanıcı Tanımları",
        description = "authorizations listesindeki her bir elemanın isActive parametresi true olanlar kaydedilir.")
public class UserAccountController {
    private static final Logger logger = LoggerFactory.getLogger(UserAccountController.class);

    final IUserAccountService service;

    public UserAccountController(IUserAccountService service) {
        this.service = service;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    public ResponseEntity addUser(@Valid @RequestBody UserAccountDto userAccountDto) {
        UserAccountDto dto = service.save(userAccountDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping("/get/{userName}")
    public UserAccountDto getUserByUsername(@PathVariable String userName) {
        return service.findByEmail(userName);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/")
    public ResponseEntity update(@Validated(OnUpdate.class) @RequestBody UserAccountDto userAccountDto) {
        service.update(userAccountDto);
        return ResponseEntity.accepted().build();
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/change-password")
    public ResponseEntity changePassword(@Valid @RequestBody ChangePasswordDto changePasswordDto) {
        service.changePassword(changePasswordDto);
        return ResponseEntity.accepted().build();
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/reset-password")
    public ResponseEntity resetPassword(@Valid @RequestBody ResetPasswordDto resetPasswordDto) {
        service.resetPassword(resetPasswordDto);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/")
    public List<UserAccountDto> findAll() {
        return service.getUserList();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public ResponseEntity remove(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/get/search")
    public List<UserAccountDto> search(@RequestParam(value = "sp") String sp) {
        return service.search(sp);
    }
}