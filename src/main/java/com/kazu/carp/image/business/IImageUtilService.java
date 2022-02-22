package com.kazu.carp.image.business;

import com.kazu.carp.image.dto.ImagePixelDto;
import com.kazu.carp.image.dto.RgbDto;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

/**
 * @author akifova
 * 13.02.2021
 */
public interface IImageUtilService {

    BufferedImage resize(byte[] source, int ratio) throws IOException;

    List<RgbDto> getColorPalette(byte[] fileData, boolean calculateColorRatio);

    List<ImagePixelDto> getPixelRatio(byte[] fileData);
}
