package app.license.business;

import com.kazu.carp.DerlemApplication;
import com.kazu.carp.app.license.business.IOshiService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @author akifova
 * 06.10.2021
 */
@ExtendWith({SpringExtension.class})
@SpringBootTest(classes = DerlemApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LicenseUtilServiceTest {

    @Autowired
    IOshiService oshiService;

    @Test
    void macAddressTest() {
        var macAddress = oshiService.getMacAddress();
        System.out.println(macAddress);
    }

    @Test
    void processorIdTest() {
        var processorId = oshiService.getProcessorId();
        System.out.println(processorId);
    }

    @Test
    void baseBoardSerialNumberTest() {
        var baseBoardSerialNumber = oshiService.getBaseBoardSerialNumber();
        System.out.println(baseBoardSerialNumber);
    }
}
