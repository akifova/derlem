package com.kazu.carp.common.firm.persistence;

import com.kazu.carp.common.firm.domain.Firm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author akifova
 * 29.04.2021
 */
public interface IFirmDao extends JpaRepository<Firm, String>, JpaSpecificationExecutor<Firm> {
}
