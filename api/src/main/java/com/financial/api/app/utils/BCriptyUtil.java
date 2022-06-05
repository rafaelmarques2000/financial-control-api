package com.financial.api.app.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCriptyUtil {
    public static String Encode(String data) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10);
        return bCryptPasswordEncoder.encode(data);
    }

    public static boolean checkPassword(String data, String encodedPassword) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10);
        return bCryptPasswordEncoder.matches(data, encodedPassword);
    }
}
