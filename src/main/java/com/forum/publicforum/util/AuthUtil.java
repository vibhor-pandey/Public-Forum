package com.forum.publicforum.util;

import java.security.MessageDigest;
import java.util.Objects;

public class AuthUtil {
    
    private static AuthUtil authUtil;
    private MessageDigest messageDigest;
    
    private static final String HASH_ALGO = "MD5";
    
    private AuthUtil() throws Exception {
        messageDigest = MessageDigest.getInstance(HASH_ALGO);
    }
    
    public static AuthUtil getInstance() throws Exception {
        if(!Objects.nonNull(authUtil)) {
            authUtil = new AuthUtil();
        }
        return authUtil;
    }
    
    public String hashData(String data) throws Exception{
        byte[] hashedBytes = null;
        hashedBytes = messageDigest.digest(data.getBytes("UTF-8"));
        return convertByteArrayToString(hashedBytes);
    }
    
    private String convertByteArrayToString(byte[] bytes) {
        
        if(bytes == null) return null;
        
        StringBuilder sb = new StringBuilder("");
        for(int b = 0; b < bytes.length; b++) {
            sb.append(Integer.toString((bytes[b] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
}
