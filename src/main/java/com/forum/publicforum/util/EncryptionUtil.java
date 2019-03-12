package com.forum.publicforum.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Objects;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;

/**
 * Singelton Class
 * 
 * @author vibhor
 *
 */

public class EncryptionUtil {

    private static SecretKeySpec  secretKey;
    private static byte[]         key;
    private static EncryptionUtil encryptionUtil;
    private Cipher cipher;
    private static final String   SECRET_KEY = "PublicForum";

    private EncryptionUtil() throws Exception {
        cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        setKey(SECRET_KEY);
    }

    public static void setKey(String myKey) {
        MessageDigest sha = null;
        try {
            key = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    
    public String encrypt(String strToEncrypt) {
        try {
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return java.util.Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }

    public String decrypt(String strToDecrypt) {
        try {
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(java.util.Base64.getDecoder().decode(strToDecrypt)));
        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }

    public static EncryptionUtil getEncryptionUtil() throws Exception {
        if (Objects.isNull(encryptionUtil)) {
            encryptionUtil = new EncryptionUtil();
        }
        return encryptionUtil;
    }

}
