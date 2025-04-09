package com.hogar360.users.commons.configurations.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncryptPasswordUtil {

    private EncryptPasswordUtil() {
        throw new UnsupportedOperationException("Utility class");
    }
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static String encrypt(String rawPassword) {
        return encoder.encode(rawPassword);
    }

    public static boolean matches(String rawPassword, String encodedPassword) {
        return encoder.matches(rawPassword, encodedPassword);
    }
}
