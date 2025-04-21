package com.hogar360.users.users.domain.ports.in;

import com.hogar360.users.users.application.dto.request.AuthenticationRequest;
import com.hogar360.users.users.application.dto.response.AuthenticationResponse;

public interface AuthenticationServicePort {
    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);
}