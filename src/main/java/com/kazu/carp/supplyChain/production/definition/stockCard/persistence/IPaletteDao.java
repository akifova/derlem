package com.kazu.carp.supplyChain.production.definition.stockCard.persistence;

import com.kazu.carp.supplyChain.production.definition.stockCard.domain.Palette;
import com.kazu.carp.supplyChain.production.definition.stockCard.dto.PaletteDto;
import com.kazu.carp.supplyChain.production.definition.stockCard.dto.PaletteFindSimilarDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author akifova
 * 07.02.2021
 */
public interface IPaletteDao extends JpaRepository<Palette, String>, JpaSpecificationExecutor<Palette> {
    PaletteDto findFirstByCode(String code);

    @Query(value = "SELECT new com.kazu.carp.supplyChain.production.definition.stockCard.dto.PaletteFindSimilarDto(p.id, p.code, count(c.code)) from Palette p " +
            "left join PaletteLine pl on p.id = pl.palette " +
            "left join Color c on c.id = pl.color " +
            "where c.code in (?1) " +
            "GROUP BY p.id, p.code order by count(c.code) desc ")
    List<PaletteFindSimilarDto> findSimilar(List<String> colorCodes);
}
