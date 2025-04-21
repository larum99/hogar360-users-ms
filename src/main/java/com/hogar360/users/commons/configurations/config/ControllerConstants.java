package com.hogar360.users.commons.configurations.config;

public class ControllerConstants {

    private ControllerConstants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String BASE_URL = "/api/v1/users";
    public static final String SAVE_PATH = "/";
    public static final String TAG = "Usuarios";
    public static final String TAG_DESCRIPTION = "Operaciones relacionadas con los usuarios";
    public static final String LOGIN_PATH = "/auth/login";
}
