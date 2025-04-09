package com.hogar360.users.users.infraestructure.exceptionshandlers;

import com.hogar360.users.users.domain.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidEmail(InvalidEmailException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(
                ExceptionConstants.INVALID_EMAIL_MESSAGE,
                LocalDateTime.now()
        ));
    }

    @ExceptionHandler(InvalidPhoneException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidPhone(InvalidPhoneException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(
                ExceptionConstants.INVALID_PHONE_MESSAGE,
                LocalDateTime.now()
        ));
    }

    @ExceptionHandler(InvalidDocumentException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidDocument(InvalidDocumentException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(
                ExceptionConstants.INVALID_DOCUMENT_MESSAGE,
                LocalDateTime.now()
        ));
    }

    @ExceptionHandler(UnderAgeUserException.class)
    public ResponseEntity<ExceptionResponse> handleUnderAgeUser(UnderAgeUserException exception) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ExceptionResponse(
                ExceptionConstants.UNDERAGE_USER_MESSAGE,
                LocalDateTime.now()
        ));
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleUserAlreadyExists(UserAlreadyExistsException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ExceptionResponse(
                ExceptionConstants.USER_ALREADY_EXISTS_MESSAGE,
                LocalDateTime.now()
        ));
    }

}
