package com.kazu.carp.supplyChain.production.definition.stockCard.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author akifova
 * 24.10.2021
 */
@Getter @Setter
public class PaletteFindSimilarDto {
    private String paletteId;
    private String paletteCode;
    private long matchColorCount;
    private BigDecimal similarityRatio;

    public PaletteFindSimilarDto(String paletteId, String paletteCode,long matchColorCount) {
        this.paletteId = paletteId;
        this.paletteCode = paletteCode;
        this.matchColorCount = matchColorCount;
    }
}
