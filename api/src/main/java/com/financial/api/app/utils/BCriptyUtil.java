package com.financial.api.app.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCriptyUtil {
    public static String Encode(String data) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(6);
        return bCryptPasswordEncoder.encode(data);
    }
}
