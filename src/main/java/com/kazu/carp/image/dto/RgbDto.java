package com.kazu.carp.image.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RgbDto {
    @Schema(title = "Desen Üzerindeki Palet Numarası")
    private int paletteNo;

    @Schema(title = "RGB- Red")
    private int red;

    @Schema(title = "RGB- Green")
    private int green;

    @Schema(title = "RGB- Blue")
    private int blue;

    @Schema(title = "Paletin Satırının Kullanıldığı Pixel Sayısı")
    private long count;

    @Schema(title = "Palet Satırının Kullanım Oranı")
    private BigDecimal ratio;

    public RgbDto() {
    }

    public RgbDto(int paletteNo, int red, int green, int blue, long count, BigDecimal ratio) {
        this.paletteNo = paletteNo;
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.count = count;
        this.ratio = ratio;
    }
}
