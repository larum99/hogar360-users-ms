package com.hogar360.users.users.domain.utils;

public class DomainConstants {
    private DomainConstants() {
        throw new IllegalStateException("Utility class");
    }

    public static final int PHONE_MAX_LENGTH = 13;
    public static final int MIN_AGE = 18;
    public static final String DEFAULT_USER_ROLE = "VENDEDOR";

    public static final String ERROR_REQUIRED_FIRSTNAME = "First name is required.";
    public static final String ERROR_REQUIRED_LASTNAME = "Last name is required.";
    public static final String ERROR_REQUIRED_DOCUMENT = "Document is required.";
    public static final String ERROR_REQUIRED_PHONE = "Phone number is required.";
    public static final String ERROR_REQUIRED_BIRTHDATE = "Birth date is required.";
    public static final String ERROR_REQUIRED_EMAIL = "Email is required.";
    public static final String ERROR_REQUIRED_PASSWORD = "Password is required.";

    public static final String ROLE_ADMIN = "ADMIN";
}
