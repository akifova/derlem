package com.kazu.carp.design.color.business;

import com.kazu.carp.design.color.domain.Color;
import com.kazu.carp.design.color.dto.ColorDto;
import com.kazu.carp.design.color.persistence.IColorDao;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author akifova
 * 08.02.2021
 */
@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackForClassName = {"Exception"})
public class ColorServiceImpl implements IColorService {
    private static final Logger logger = LoggerFactory.getLogger(ColorServiceImpl.class);

    final IColorDao dao;

    public ColorServiceImpl(IColorDao dao) {
        this.dao = dao;
    }

    @Override
    public Color findByRgbRedAndRgbGreenAndRgbBlue(Integer rgbRed, Integer rgbGreen, Integer rgbBlue) {
        return dao.findByRgbRedAndRgbGreenAndRgbBlue(rgbRed, rgbGreen, rgbBlue);
    }

    @Override
    public Color createNewFromDto(ColorDto color) {
        String red = String.format("%03d", color.getRgbRed());
        String green = String.format("%03d", color.getRgbGreen());
        String blue = String.format("%03d", color.getRgbBlue());

        Color clr = new Color();
        clr.setCode(String.format("%s.%s.%s", red, green, blue));
        clr.setDefinition(color.getDefinition());
        clr.setRgbRed(color.getRgbRed());
        clr.setRgbGreen(color.getRgbGreen());
        clr.setRgbBlue(color.getRgbBlue());
        return clr;
    }

    @Override
    public Color getFromDto(ColorDto color) {
        if(color == null) {
            return null;
        }

        if(!StringUtils.isEmpty(color.getId())) {
            return dao.getById(color.getId());
        }

        Color clr = findByRgbRedAndRgbGreenAndRgbBlue(color.getRgbRed(), color.getRgbGreen(), color.getRgbBlue());

        if(clr == null) {
            clr = createNewFromDto(color);
        }
        return clr;
    }
}