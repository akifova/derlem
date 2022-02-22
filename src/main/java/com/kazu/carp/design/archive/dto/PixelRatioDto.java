package com.kazu.carp.design.archive.dto;

import com.kazu.carp.app.jpa.BaseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author akifova
 * 08.06.2021
 */
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class PixelRatioDto extends BaseDto {
    private short versionNumber;
    private LocalDateTime versionDate;
    private Integer paletteNo;
    private Long count;
    private Integer rgbRed;
    private Integer rgbGreen;
    private Integer rgbBlue;
    private BigDecimal ratio;
    private ArchiveDto archive;
}