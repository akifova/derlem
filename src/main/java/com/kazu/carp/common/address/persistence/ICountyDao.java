package com.kazu.carp.common.address.persistence;


import com.kazu.carp.common.address.domain.County;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ICountyDao extends JpaRepository<County, String>, JpaSpecificationExecutor<County> {
}
