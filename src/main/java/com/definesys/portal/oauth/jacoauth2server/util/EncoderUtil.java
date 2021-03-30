package com.definesys.portal.oauth.jacoauth2server.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @description:
 * @date: 2021/3/30 2:21 下午
 * @author: kerry
 */
public class EncoderUtil {
    /**
     * md5 加密
     * @param password
     * @return
     */
    public static String encryptByMd5(String password){
        MessageDigest md5= null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        BASE64Encoder base64en = new BASE64Encoder();

        String encipassword= null;
        try {
            encipassword = base64en.encode(md5.digest(password.getBytes("utf-8")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encipassword;
    }

    /**
     * BCrypt 加密
     * @param password
     * @return
     */
    public static String encryptByBCrypt(String password){
        BCryptPasswordEncoder passwordEncoder= new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }


    public static void main(String[] args) {
        String a="abcdefg";
        for(int i=0;i<5;i++) {
            System.out.println(a + ":" + encryptByMd5(a));
        }
    }
}
