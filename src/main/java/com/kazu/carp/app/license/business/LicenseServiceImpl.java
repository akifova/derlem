package com.kazu.carp.app.license.business;

import com.kazu.carp.app.license.domain.License;
import com.kazu.carp.app.license.dto.ILicenseMapper;
import com.kazu.carp.app.license.dto.LicenseDto;
import com.kazu.carp.app.license.persistence.ILicenseDao;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

/**
 * @author akifova
 * 05.10.2021
 */
@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackForClassName = {"Exception"})
public class LicenseServiceImpl implements ILicenseService {

    final ILicenseCreatorService creatorService;
    final ILicenseDao dao;
    final ILicenseMapper mapper;
    final IOshiService oshiService;

    public LicenseServiceImpl(ILicenseCreatorService creatorService, ILicenseDao dao, ILicenseMapper mapper, IOshiService oshiService) {
        this.creatorService = creatorService;
        this.dao = dao;
        this.mapper = mapper;
        this.oshiService = oshiService;
    }

    @Override
    @CacheEvict(value = "licenses", allEntries = true)
    public void addLicense(LicenseDto licenseDto) {
        LicenseDto dto = creatorService.create(licenseDto);
        if(licenseDto.getLicense().equals(dto.getLicense())) {
            License license = License.builder()
                    .endDate(licenseDto.getEndDate())
                    .macAddress(licenseDto.getMacAddress())
                    .processorId(licenseDto.getProcessorId())
                    .baseBoardSerialNumber(licenseDto.getBaseBoardSerialNumber())
                    .license(licenseDto.getLicense())
                    .build();
            dao.save(license);
        }
    }

    @Override
    @Cacheable(value = "licenses")
    public List<LicenseDto> getActiveLicenses() {
        List<License> all = dao.findAll();
        if(all.size() == 0) {
            return null;
        }
        return all.stream()
                .filter(f-> f.getEndDate().compareTo(LocalDate.now()) >= 0)
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<LicenseDto> getAllLicenses() {
        List<License> all = dao.findAll();
        if(all.size()==0) {
            return null;
        }
        return all.stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public boolean verifyLicense() {
        List<LicenseDto> activeLicenses = getActiveLicenses();
        String macAddress = oshiService.getMacAddress();
        String processorId = oshiService.getProcessorId();
        String baseBoardSerialNumber = oshiService.getBaseBoardSerialNumber();

        if(activeLicenses == null || activeLicenses.size() <= 0 || StringUtils.isEmpty(macAddress) ||
        StringUtils.isEmpty(processorId) || StringUtils.isEmpty(baseBoardSerialNumber)) {
            return false;
        }

        AtomicBoolean verified = new AtomicBoolean(false);
        activeLicenses.stream().forEach(l-> {
            LicenseDto checker = creatorService.create(l);
            if(checker.getLicense().equals(l.getLicense())) {
                verified.set(true);
            }
        });

        return verified.get();
    }
}