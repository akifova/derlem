package app.license.business;

import com.kazu.carp.DerlemApplication;
import com.kazu.carp.app.license.business.ILicenseService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @author akifova
 * 08.10.2021
 */
@ExtendWith({SpringExtension.class})
@SpringBootTest(classes = DerlemApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LicenseServiceTest {
    private static final Logger logger = LoggerFactory.getLogger(LicenseServiceTest.class);

    @Autowired
    ILicenseService licenseService;

    @Test
    public void verifyLicense() {
        var result = licenseService.verifyLicense();
        logger.info(String.valueOf(result));
    }
}
