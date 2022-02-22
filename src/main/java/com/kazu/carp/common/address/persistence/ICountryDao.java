package com.kazu.carp.common.address.persistence;


import com.kazu.carp.common.address.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ICountryDao extends JpaRepository<Country, String>, JpaSpecificationExecutor<Country> {
}
