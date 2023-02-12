package com.vn.bookstore.encrypt;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class EncryptPass {
    public static String SHA1(String arg)
    {
        String salt = "hfsk12@._9)+='://t%&12$#,^-^";
        String reusltSHA1 = "";
        reusltSHA1=arg+salt;
        try {
            byte[] dataByte = arg.getBytes("UTF-8");
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            reusltSHA1= Base64.getMimeEncoder().encodeToString(messageDigest.digest(dataByte));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        return  reusltSHA1;
    }

    public static void main(String[] args) {

    }
}
