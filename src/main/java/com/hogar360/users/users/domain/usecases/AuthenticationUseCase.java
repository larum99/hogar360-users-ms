package com.hogar360.users.users.domain.usecases;

import com.hogar360.users.users.application.dto.request.AuthenticationRequest;
import com.hogar360.users.users.domain.exceptions.InvalidCredentialsException;
import com.hogar360.users.users.domain.model.UserModel;
import com.hogar360.users.users.domain.ports.in.AuthenticationServicePort;
import com.hogar360.users.users.domain.ports.out.PasswordEncoderPort;
import com.hogar360.users.users.domain.ports.out.UserPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class AuthenticationUseCase implements AuthenticationServicePort {

    private final UserPersistencePort userPersistencePort;
    private final PasswordEncoderPort passwordEncoderPort;

    @Override
    public UserModel authenticate(AuthenticationRequest authenticationRequest) {
        return validateCredentials(authenticationRequest);
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
