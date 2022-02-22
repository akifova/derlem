package com.kazu.carp.common.address.persistence;

import com.kazu.carp.common.address.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IAddressDao extends JpaRepository<Address, String> {
    Address findByCountyIdAndAddress(String countyId, String addressStr);

    List<Address> findByFirmsId(String firmId);
}
