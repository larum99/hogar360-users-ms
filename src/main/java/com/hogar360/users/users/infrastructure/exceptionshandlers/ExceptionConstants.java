package com.hogar360.users.users.infraestructure.exceptionshandlers;

public class ExceptionConstants {
    private ExceptionConstants(){}
    public static final String INVALID_EMAIL_MESSAGE = "El correo electrónico no tiene un formato válido.";
    public static final String INVALID_PHONE_MESSAGE = "El número de teléfono no es válido o excede el tamaño permitido.";
    public static final String INVALID_DOCUMENT_MESSAGE = "El documento de identidad no tiene un formato válido.";
    public static final String UNDERAGE_USER_MESSAGE = "El usuario debe ser mayor de edad para registrarse.";
    public static final String USER_ALREADY_EXISTS_MESSAGE = "Ya existe un usuario registrado con el correo proporcionado.";
}
