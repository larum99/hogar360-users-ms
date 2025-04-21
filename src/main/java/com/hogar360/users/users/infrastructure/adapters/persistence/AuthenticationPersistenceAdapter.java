package com.hogar360.users.users.infrastructure.adapters.persistence;

import com.hogar360.users.users.domain.model.UserModel;
import com.hogar360.users.users.domain.ports.out.AuthenticationPersistencePort;
import com.hogar360.users.users.infrastructure.security.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthenticationPersistenceAdapter implements AuthenticationPersistencePort {

    private final JwtUtil jwtUtil;

    @Override
    public String generateToken(UserModel user) {
        return jwtUtil.generateToken(user);
    }
}
