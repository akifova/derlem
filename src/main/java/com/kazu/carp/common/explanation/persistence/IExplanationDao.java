package com.kazu.carp.common.explanation.persistence;

import com.kazu.carp.common.explanation.domain.Explanation;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author akifova
 * 14.02.2021
 */
public interface IExplanationDao extends JpaRepository<Explanation, String> {
}
