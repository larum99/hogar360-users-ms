package com.hogar360.users.users.domain.utils;

import java.util.regex.Pattern;

public class RegexUtils {
    public static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");

    public static final Pattern DOCUMENT_PATTERN =
            Pattern.compile("^\\d+$");

    public static final Pattern PHONE_PATTERN =
            Pattern.compile("^\\+?[0-9]{7,15}$"); // Ejemplo de patrón para teléfonos
}
