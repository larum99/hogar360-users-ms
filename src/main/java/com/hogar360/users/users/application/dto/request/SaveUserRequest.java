package com.hogar360.users.users.application.dto.request;

import java.time.LocalDate;

public record SaveUserRequest(String firstName,
                              String lastName,
                              String identityDocument,
                              String phoneNumber,
                              LocalDate birthDate,
                              String email,
                              String password,
                              String role) {
}
