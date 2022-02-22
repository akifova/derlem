package com.kazu.carp.image.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class ImagePixelDto {

    @Schema(title = "Desen Üzerindeki Palet Numarası")
    private int paletteNo;

    @Schema(title = "Paletin Satırının Kullanıldığı Pixel Sayısı")
    private long count;

    @Schema(title = "Palet Satırının Kullanım Oranı")
    private BigDecimal ratio;
}
