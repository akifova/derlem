package com.kazu.carp.yarn.definition.presentation;

import com.kazu.carp.app.validation.common.OnUpdate;
import com.kazu.carp.yarn.definition.business.IYarnTypeService;
import com.kazu.carp.yarn.definition.dto.YarnTypeDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.Explode;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.enums.ParameterStyle;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
 * 01.05.2021
 */
@RestController
@RequestMapping("/yarn/type")
@Tag(name = "İplik Tipi", description = "")
public class YarnTypeController {
    private static final Logger logger = LoggerFactory.getLogger(YarnTypeController.class);

    final IYarnTypeService service;

    public YarnTypeController(IYarnTypeService service) {
        this.service = service;
    }

    @Operation(tags = "yarn", summary = "İplik Tipi Ekle", description = "İplik Tipi ekle")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = YarnTypeDto.class)) }),
            @ApiResponse(responseCode = "405", description = "Invalid input")
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    public ResponseEntity<YarnTypeDto> create(@Valid @RequestBody YarnTypeDto yarnTypeDto) {
        YarnTypeDto dto = service.create(yarnTypeDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @Operation(tags = "yarn", summary = "İplik Tipi Güncelle", description = "İplik Tipi Güncelle")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = YarnTypeDto.class)) }),
            @ApiResponse(responseCode = "405", description = "Invalid input")
    })
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/")
    public ResponseEntity update(@Validated(OnUpdate.class) @RequestBody YarnTypeDto yarnTypeDto) {
        service.update(yarnTypeDto);
        return ResponseEntity.accepted().build();
    }

    @Operation(tags = "yarn", summary = "İplik Tipi Listesi", description = "İplik Tipi Listesi")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = YarnTypeDto.class)) }),
            @ApiResponse(responseCode = "405", description = "Invalid input")
    })
    @GetMapping("/")
    public List<YarnTypeDto> findAll() {
        return service.findAll();
    }

    @Operation(tags = "yarn", summary = "ID Üzerinden İplik Sorgulama", description = "ID Üzerinden İplik Sorgulama")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = YarnTypeDto.class)) }),
            @ApiResponse(responseCode = "405", description = "Invalid input")
    })
    @GetMapping("/{id}")
    public YarnTypeDto findOne(
            @Parameter(description = "İplik Tipi ID'si", explode = Explode.TRUE, in = ParameterIn.QUERY, name = "tags", style = ParameterStyle.SIMPLE)
            @PathVariable String id) {
        return service.findOne(id);
    }

    @Operation(tags = "yarn", summary = "İplik Tipi Silme", description = "İplik Tipi Silme")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = YarnTypeDto.class)) }),
            @ApiResponse(responseCode = "405", description = "Invalid input")
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public ResponseEntity remove(
            @Parameter(description = "İplik Tipi ID'si", explode = Explode.TRUE, in = ParameterIn.QUERY, name = "tags", style = ParameterStyle.SIMPLE)
            @PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}