package com.hogar360.users.users.infrastructure.exceptionshandlers;

public class ExceptionConstants {
    private ExceptionConstants(){}
    public static final String INVALID_EMAIL_MESSAGE = "The email address is invalid.";
    public static final String INVALID_PHONE_MESSAGE = "The phone number is invalid.";
    public static final String INVALID_DOCUMENT_MESSAGE = "The identity document is invalid.";
    public static final String UNDERAGE_USER_MESSAGE = "User is under the minimum required age.";
    public static final String USER_ALREADY_EXISTS_MESSAGE = "A user with this email already exists.";
    public static final String FORBIDDEN_MESSAGE = "Access denied: insufficient permissions.";
}
