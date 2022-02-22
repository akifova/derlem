package com.kazu.carp.design.archive.business;

import com.kazu.carp.app.exception.design.OnlyProductsCanBeRetiredException;

/**
 * @author akifova
 * 17.07.2021
 */
public interface IArchiveShelveService {
    void convertToStock(String archiveId);

    void convertToUnusable(String archiveId);

    void convertToRetired(String archiveId) throws OnlyProductsCanBeRetiredException;
}
