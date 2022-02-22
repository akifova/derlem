package app;

import com.kazu.carp.DerlemApplication;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @author aova
 * @since 08.12.2020
 */
@ExtendWith({SpringExtension.class})
@SpringBootTest(classes = DerlemApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserPasswordTest {
    private static final Logger logger = LoggerFactory.getLogger(UserPasswordTest.class);

    @Test
    public void createPassword() {
        PasswordEncoder pe = new BCryptPasswordEncoder();
        String newPass = "Kazu1923@";
        String encodedPass = pe.encode(newPass);
        System.out.println(encodedPass);
        boolean result = pe.matches(newPass, encodedPass);
        Assertions.assertTrue(result);
    }

}
