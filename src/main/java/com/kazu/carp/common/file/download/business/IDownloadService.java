package com.kazu.carp.common.file.download.business;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author akifova
 * 15.08.2021
 */
public interface IDownloadService {
    void bmpByArchiveId(String archiveId, boolean includeSupplementary, HttpServletResponse response) throws IOException;

    void jpgByArchiveId(String archiveId, HttpServletResponse response, boolean convertBase64Format) throws IOException;
}
