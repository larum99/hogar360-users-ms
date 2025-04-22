package com.hogar360.users.users.application.services;

import com.hogar360.users.users.application.dto.request.AuthenticationRequest;
import com.hogar360.users.users.application.dto.response.AuthenticationResponse;

public interface AuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);
}
