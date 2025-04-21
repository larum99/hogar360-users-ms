package com.hogar360.users.users.infrastructure.security;

import com.hogar360.users.users.domain.ports.in.RoleValidatorPort;
import com.hogar360.users.users.infrastructure.security.utils.JwtUtil;
import org.springframework.stereotype.Component;

@Component
public class JwtRoleValidator implements RoleValidatorPort {

    private final JwtUtil jwtUtil;

    public JwtRoleValidator(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public String extractRole(String token) {
        return jwtUtil.extractRole(token);
    }
}
