package com.kazu.carp.image.business;

import com.kazu.carp.app.exception.design.CannotReadRgbException;
import com.kazu.carp.image.dto.ImagePixelDto;
import com.kazu.carp.image.dto.RgbDto;
import ij.ImagePlus;
import ij.process.ImageProcessor;
import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author akifova
 * 13.02.2021
 */
@Service
public class ImageUtilServiceImpl implements IImageUtilService {
    private static final Logger logger = LoggerFactory.getLogger(ImageUtilServiceImpl.class);

    @Override
    public BufferedImage resize(byte[] source, int ratio) throws IOException {
        //byte to BufferedImage
        InputStream in = new ByteArrayInputStream(source);
        BufferedImage bufferedImage = ImageIO.read(in);

        int thumbnailWidth = bufferedImage.getWidth() * (1- (ratio / 100));
        int thumbnailHeight = bufferedImage.getHeight() * (1- (ratio / 100));

        return Scalr.resize(bufferedImage, Scalr.Method.QUALITY, thumbnailWidth, thumbnailHeight, Scalr.OP_ANTIALIAS);
    }

    @Override
    public List<RgbDto> getColorPalette(byte[] fileData, boolean calculateColorRatio) {
        BufferedImage bi = null;
        try {
            bi = ImageIO.read(new ByteArrayInputStream(fileData));
            ImagePlus ip = new ImagePlus("", bi);
            List<RgbDto> rgbs = new ArrayList<>();

            List<ImagePixelDto> pixelratios = null;
            if(calculateColorRatio) {
                pixelratios = getPixelRatio(fileData);
            }

            for (int i=0; i<256; i++) {
                Integer red = ip.getProcessor().getColorModel().getRed(i);
                Integer green = ip.getProcessor().getColorModel().getGreen(i);
                Integer blue = ip.getProcessor().getColorModel().getBlue(i);
                String controlData = red.toString() + green.toString() + blue.toString();
                if (!("00255".equals(controlData) || "000".equals(controlData) || "0255255".equals(controlData))) {
                    if(pixelratios == null) {
                        rgbs.add(new RgbDto(i, red, green, blue, 0, BigDecimal.ZERO));
                    } else {
                        //Pixel Sayı ve Oranları yazdırılıyor
                        int finalI = i;
                        if(pixelratios.stream().anyMatch(m->m.getPaletteNo()== finalI && m.getCount() > 0)) {
                            ImagePixelDto ratDto = pixelratios.stream().filter(f->f.getPaletteNo()==finalI).findFirst().orElse(null);
                            if(ratDto != null) {
                                rgbs.add(new RgbDto(i, red, green, blue, ratDto.getCount(), ratDto.getRatio()));
                            }
                        }
                    }
                }
            }

            if (rgbs.size() > 0){
                return rgbs;
            } else {
                throw new CannotReadRgbException();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (CannotReadRgbException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public List<ImagePixelDto> getPixelRatio(byte[] fileData) {
        BufferedImage bi = null;
        try {
            bi = ImageIO.read(new ByteArrayInputStream(fileData));
            ImagePlus ip = new ImagePlus("", bi);

            if (ip.getProcessor() == null || ip.getProcessor().getPixels() == null) {
                return null;
            }

            ImageProcessor processor = ip.getProcessor();
            List<Integer> pixels = new ArrayList<>();
            for(int i =0; i< processor.getPixelCount();i++) {
                pixels.add(processor.get(i));
            }

            List<ImagePixelDto> pixelRatios = new ArrayList<>();
            pixels.stream()
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                    .forEach((g,r)-> {
                        ImagePixelDto dto = new ImagePixelDto();
                        dto.setPaletteNo(g);
                        dto.setCount(r);
                        pixelRatios.add(dto);
                    });

            BigDecimal totalCount = new BigDecimal(pixelRatios.stream().mapToLong(i->i.getCount()).sum());

            pixelRatios.stream().forEach(e-> {
                BigDecimal count = new BigDecimal(e.getCount());
                e.setRatio(count.divide(totalCount, 4, RoundingMode.HALF_UP).multiply(new BigDecimal(100)));
            });

            return pixelRatios;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
