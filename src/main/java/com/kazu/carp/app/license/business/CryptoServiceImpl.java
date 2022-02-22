package com.kazu.carp.app.license.business;

import com.kazu.carp.app.license.dto.CryptoConstants;
import org.springframework.stereotype.Service;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

/**
 * @author akifova
 * 01.10.2021
 */
@Service
public class CryptoServiceImpl implements ICryptoService {

    @Override
    public String encrypt(String value) {
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance(CryptoConstants.ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, getKey(), getSpec());
            byte[] cipherText = cipher.doFinal(value.getBytes());
            return Base64.getEncoder()
                    .encodeToString(cipherText);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public String decrypt(String encryptedValue) {
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance(CryptoConstants.ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, getKey(), getSpec());
            byte[] plainText = cipher.doFinal(Base64.getDecoder()
                    .decode(encryptedValue));
            return new String(plainText);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }

        return "";
    }

    private IvParameterSpec getSpec() {
        byte[] iv = {1, 9, 2, 3, 1, 9, 1, 9, 1, 8, 8, 1, 1, 9, 3, 8};
        return new IvParameterSpec(CryptoConstants.IV);
    }

    private SecretKey getKey()
    {
        byte[] key;
        MessageDigest sha = null;
        try {
            key = CryptoConstants.SK.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            return new SecretKeySpec(key, "AES");
        }
        catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
}