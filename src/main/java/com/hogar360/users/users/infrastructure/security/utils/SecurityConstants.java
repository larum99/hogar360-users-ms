package com.hogar360.users.users.infrastructure.security.utils;

import java.util.List;

public class SecurityConstants {

    private SecurityConstants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String EMAIL_CLAIM = "email";
    public static final String ROLE_CLAIM = "role";
    public static final String ID_CLAIM = "id";


    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";

    public static final String USERS_AUTH_PATH = "/api/v1/users/auth/**";
    public static final String CATEGORY_PATH = "/api/v1/category/**";
    public static final String SWAGGER_UI_PATH = "/swagger-ui/**";
    public static final String SWAGGER_UI_HTML_PATH = "/swagger-ui.html";
    public static final String API_DOCS_PATH = "/api-docs/**";
    public static final String API_DOCS_PATH_NO_SLASH = "/api-docs";

    public static final String ALLOWED_ORIGIN = "http://localhost:8091";
    public static final List<String> ALLOWED_METHODS = List.of("GET", "POST", "PUT", "DELETE");
    public static final List<String> ALLOWED_HEADERS = List.of("*");

}
