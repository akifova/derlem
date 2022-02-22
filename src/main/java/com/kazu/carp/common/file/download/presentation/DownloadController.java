package com.kazu.carp.common.file.download.presentation;

import com.kazu.carp.common.file.download.business.IDownloadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author akifova
 * 15.08.2021
 */
@RestController
@RequestMapping("/file/download")
public class DownloadController {
    private static final Logger logger = LoggerFactory.getLogger(DownloadController.class);

    final IDownloadService service;

    public DownloadController(IDownloadService service) {
        this.service = service;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping(value = "/bmp/by-archive-id/{archiveId}", produces="image/bmp")
    public void bmp(@PathVariable String archiveId, HttpServletResponse response) throws IOException {
        service.bmpByArchiveId(archiveId, false, response);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping(value = "/jpg/by-archive-id/{archiveId}", produces="image/jpeg")
    public void jpg(@PathVariable String archiveId, HttpServletResponse response) throws IOException {
        service.jpgByArchiveId(archiveId, response, false);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping(value = "/jpg/base64/by-archive-id/{archiveId}", produces="image/jpeg")
    public void jpgBase64(@PathVariable String archiveId, HttpServletResponse response) throws IOException {
        service.jpgByArchiveId(archiveId, response, true);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping(value = "/bmp/supplementary/by-archive-id/{archiveId}", produces="application/zip")
    public void bmpIncludeSupplementary(@PathVariable String archiveId, HttpServletResponse response) throws IOException {
        service.bmpByArchiveId(archiveId, true, response);
    }


}