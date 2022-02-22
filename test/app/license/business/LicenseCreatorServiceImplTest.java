package app.license.business;

import com.kazu.carp.DerlemApplication;
import com.kazu.carp.app.license.business.ILicenseCreatorService;
import com.kazu.carp.app.license.dto.LicenseDto;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

/**
 * @author akifova
 * 02.10.2021
 */
@ExtendWith({SpringExtension.class})
@SpringBootTest(classes = DerlemApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LicenseCreatorServiceImplTest {

    @Autowired
    ILicenseCreatorService service;

    @Test
    void create() {
        LicenseDto dto = LicenseDto.builder()
                .endDate(LocalDate.now().plusDays(365))
                .macAddress("e0:69:95:d7:44:2b")
                .baseBoardSerialNumber("BTWW12501DBS")
                .processorId("BFEBFBFF000206A7")
                .build();
        var license = service.create(dto);
        System.out.println(String.format("End Date: %s\nMac Address: %s\nLicense: %s\nProcessorId: %s\nBase Board Serial Number: %s",
                license.getEndDate(), license.getMacAddress(), license.getLicense(), license.getProcessorId(), license.getBaseBoardSerialNumber()));
    }
}