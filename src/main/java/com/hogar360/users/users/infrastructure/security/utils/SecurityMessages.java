package com.hogar360.users.users.infrastructure.security.utils;

public class SecurityMessages {

    private SecurityMessages() {
        throw new IllegalStateException("Utility class");
    }
    public static final String EXPIRED_TOKEN_MESSAGE = "Expired Token";
    public static final String INVALID_TOKEN_MESSAGE = "Invalid Token";
}
