package com.kazu.carp.app.license.persistence;

import com.kazu.carp.app.license.domain.License;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author akifova
 * 05.10.2021
 */
public interface ILicenseDao extends JpaRepository<License, String> {
}
