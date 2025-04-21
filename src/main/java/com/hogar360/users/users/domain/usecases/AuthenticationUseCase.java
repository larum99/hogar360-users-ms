package com.hogar360.users.users.domain.usecases;

import com.hogar360.users.users.application.dto.request.AuthenticationRequest;
import com.hogar360.users.users.application.dto.response.AuthenticationResponse;
import com.hogar360.users.users.domain.exceptions.InvalidCredentialsException;
import com.hogar360.users.users.domain.model.UserModel;
import com.hogar360.users.users.domain.ports.in.AuthenticationServicePort;
import com.hogar360.users.users.domain.ports.out.PasswordEncoderPort;
import com.hogar360.users.users.domain.ports.out.UserPersistencePort;
import com.hogar360.users.users.domain.ports.out.AuthenticationPersistencePort;

import java.util.Optional;

public class AuthenticationUseCase implements AuthenticationServicePort {

    private final UserPersistencePort userPersistencePort;
    private final PasswordEncoderPort passwordEncoderPort;
    private final AuthenticationPersistencePort authenticationPersistencePort;

    public AuthenticationUseCase(UserPersistencePort userPersistencePort, PasswordEncoderPort passwordEncoderPort, AuthenticationPersistencePort authenticationPersistencePort) {
        this.userPersistencePort = userPersistencePort;
        this.passwordEncoderPort = passwordEncoderPort;
        this.authenticationPersistencePort = authenticationPersistencePort;
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        UserModel user = validateCredentials(authenticationRequest);

        String token = authenticationPersistencePort.generateToken(user);

        return new AuthenticationResponse(token);
    }

    private UserModel validateCredentials(AuthenticationRequest authenticationRequest) {
        Optional<UserModel> optionalUser = Optional.ofNullable(userPersistencePort.getUserByEmail(authenticationRequest.email()));

        UserModel user = optionalUser.orElseThrow(InvalidCredentialsException::new);

        if (!passwordEncoderPort.matches(authenticationRequest.password(), user.getPassword())) {
            throw new InvalidCredentialsException();
        }

        return user;
    }
}
