package com.kazu.carp.supplyChain.production.definition.stockCard.dto;

import com.kazu.carp.app.config.MapStructConfig;
import com.kazu.carp.supplyChain.production.definition.stockCard.domain.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @author akifova
 * 06.02.2021
 */
@Mapper(config = MapStructConfig.class)
public interface IStockCardDefinitionMapper {
    Quality toEntity(QualityDto qualityDto);
    QualityDto toDto(Quality quality);

    @Mapping(target = "brand", ignore = true)
    Collection toEntity(CollectionDto collectionDto);
    @Mapping(target = "brand", ignore = true)
    CollectionDto toDto(Collection collection);

    Design toEntity(DesignDto designDto);
    DesignDto toDto(Design design);

    @Mapping(target="paletteLines", ignore = true)
    Palette toEntity(PaletteDto paletteDto);
    @Mapping(target="paletteLines", ignore = true)
    PaletteDto toDto(Palette palette);

    @Mappings({
            @Mapping(target="lineToYarns", ignore = true),
            @Mapping(target="color", ignore = true),
            @Mapping(target="palette", ignore = true)
    })
    PaletteLine toEntity(PaletteLineDto paletteLineDto);
    @Mappings({
            @Mapping(target="lineToYarns", ignore = true),
            @Mapping(target="color", ignore = true),
            @Mapping(target="palette", ignore = true)
    })
    PaletteLineDto toDto(PaletteLine palette);

    @Mappings({
            @Mapping(target="paletteLine", ignore = true),
            @Mapping(target="yarn", ignore = true)
    })
    PaletteLineToYarn toEntity(PaletteLineToYarnDto yarnDto);
    @Mappings({
            @Mapping(target="paletteLine", ignore = true),
            @Mapping(target="yarn", ignore = true)
    })
    PaletteLineToYarnDto toDto(PaletteLineToYarn yarn);

    Edge toEntity(EdgeDto edgeDto);
    EdgeDto toDto(Edge edge);

    Size toEntity(SizeDto sizeDto);
    SizeDto toDto(Size size);

    Shape toEntity(ShapeDto shapeDto);
    ShapeDto toDto(Shape shape);
}
