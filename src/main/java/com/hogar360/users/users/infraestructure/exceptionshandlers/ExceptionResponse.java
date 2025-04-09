package com.hogar360.users.users.infraestructure.exceptionshandlers;

import java.time.LocalDateTime;

public class ExceptionResponse {
    private final String message;
    private final LocalDateTime timestamp;

    public ExceptionResponse(String message, LocalDateTime timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
