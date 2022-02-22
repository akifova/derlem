package com.kazu.carp.image.presentation;

import com.kazu.carp.image.business.IImageUtilService;
import com.kazu.carp.image.dto.ImagePixelDto;
import com.kazu.carp.image.dto.RgbDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author akifova
 * 31.05.2021
 */
@RestController
@RequestMapping("/image")
@Tag(name = "BMP Image İşlemleri", description = "")
public class ImageUtilController {
    private static final Logger logger = LoggerFactory.getLogger(ImageUtilController.class);

    final IImageUtilService service;

    public ImageUtilController(IImageUtilService service) {
        this.service = service;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/color")
    public List<RgbDto> getColorPalette(@RequestParam("file") MultipartFile file) throws IOException {
        return service.getColorPalette(file.getBytes(), false);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/color-ratio")
    public List<RgbDto> getColorAndRatio(@RequestParam("file") MultipartFile file) throws IOException {
        return service.getColorPalette(file.getBytes(), true);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/ratio")
    public List<ImagePixelDto> getPixelRatio(@RequestParam("file") MultipartFile file) throws IOException {
        return service.getPixelRatio(file.getBytes());
    }
}