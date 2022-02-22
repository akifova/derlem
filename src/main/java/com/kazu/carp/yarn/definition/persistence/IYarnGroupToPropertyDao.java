package com.kazu.carp.yarn.definition.persistence;

import com.kazu.carp.yarn.definition.domain.YarnGroupToProperty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author akifova
 * 06.05.2021
 */
public interface IYarnGroupToPropertyDao extends JpaRepository<YarnGroupToProperty, String> {
    List<YarnGroupToProperty> findByYarnGroupId(String groupId);

    YarnGroupToProperty findFirstByYarnGroupIdAndYarnPropertyId(String groupId, String propertyId);
}
