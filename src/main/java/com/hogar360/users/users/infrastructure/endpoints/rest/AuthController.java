package com.hogar360.users.users.infrastructure.endpoints.rest;

import com.hogar360.users.commons.configurations.config.AuthControllerDocs.LoginDocs;
import com.hogar360.users.commons.configurations.config.ControllerConstants;
import com.hogar360.users.users.application.dto.request.AuthenticationRequest;
import com.hogar360.users.users.application.dto.response.AuthenticationResponse;
import com.hogar360.users.users.application.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ControllerConstants.BASE_URL)
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @LoginDocs
    @PostMapping(ControllerConstants.LOGIN_PATH)
    public AuthenticationResponse authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        return authenticationService.authenticate(authenticationRequest);

    }
}
