package com.hogar360.users.users.domain.ports.in;

public interface RoleValidatorPort {
    String extractRole(String token);
}
