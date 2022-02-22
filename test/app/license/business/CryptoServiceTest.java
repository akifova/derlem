package app.license.business;

import com.kazu.carp.DerlemApplication;
import com.kazu.carp.app.license.business.ICryptoService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author akifova
 * 01.10.2021
 */
@ExtendWith({SpringExtension.class})
@SpringBootTest(classes = DerlemApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CryptoServiceTest {

    @Autowired
    ICryptoService cryptoService;

    String cryptText;

    @Test
    @Order(1)
    void encryptionTest() throws InvalidAlgorithmParameterException, NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        var str = cryptoService.encrypt("Deneme");
        cryptText = str;
        //System.out.println(cryptText);
        Assertions.assertEquals("AI8Nb3ASPVNuTWDyxVGTtw==", str);
    }

    @Test
    @Order(2)
    void decryptionTest() throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        var str = cryptoService.decrypt(cryptText);
        Assertions.assertEquals("Deneme", str);
        //System.out.println(str);
    }

    @Test
    @Order(3)
    void encryptionTest2() throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        var enc = "0zOBPzNHP7gJjErqFbZOug==";
        var enc2 = "ZI4qozmCc8kSvUUHzZ+PvA==";
        var enc3 = "vRFQiv4oBMgXo3Ftgu7UawBEpswbaI7r+4T+n+FCOsQ=";
        var enc4 = "nO4bxcXq2bxS+UWv5ihiRA==";
        var enc5 = "/ypf6odhMN736X7eJ5A3yg==";
        var enc6 = "BdGIy4lOn9dg20FGpvwwgQ==";
        var enc7 = "JByb4n4GoTwWDe00ElsIHQ==";
        var enc8 = "P31kIzP/IsmXED79lcbqbw==";
        var enc9 = "9jC6rAnNpBNK6KMgS0DcMA==";
        System.out.println(String.format("%s ==> %s", enc, cryptoService.decrypt(enc)));
        System.out.println(String.format("%s ==> %s", enc2, cryptoService.decrypt(enc2)));
        System.out.println(String.format("%s ==> %s", enc3, cryptoService.decrypt(enc3)));
        System.out.println(String.format("%s ==> %s", enc4, cryptoService.decrypt(enc4)));
        System.out.println(String.format("%s ==> %s", enc5, cryptoService.decrypt(enc5)));
        System.out.println(String.format("%s ==> %s", enc6, cryptoService.decrypt(enc6)));
        System.out.println(String.format("%s ==> %s", enc7, cryptoService.decrypt(enc7)));
        System.out.println(String.format("%s ==> %s", enc8, cryptoService.decrypt(enc8)));
        System.out.println(String.format("%s ==> %s", enc9, cryptoService.decrypt(enc9)));
    }
}
