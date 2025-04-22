package com.hogar360.users.users.application.services.impl;

import com.hogar360.users.users.application.dto.request.AuthenticationRequest;
import com.hogar360.users.users.application.dto.response.AuthenticationResponse;
import com.hogar360.users.users.application.services.AuthenticationService;
import com.hogar360.users.users.domain.model.UserModel;
import com.hogar360.users.users.domain.ports.in.AuthenticationServicePort;
import com.hogar360.users.users.domain.ports.out.AuthenticationPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationServicePort authenticationServicePort;
    private final AuthenticationPersistencePort authenticationPersistencePort;

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        UserModel user = authenticationServicePort.authenticate(authenticationRequest);

        String token = authenticationPersistencePort.generateToken(user);

        return new AuthenticationResponse(token);
    }
}
