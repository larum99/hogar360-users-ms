package com.hogar360.users.users.domain.usecases;

import com.hogar360.users.users.application.dto.request.AuthenticationRequest;
import com.hogar360.users.users.application.dto.response.AuthenticationResponse;
import com.hogar360.users.users.domain.exceptions.InvalidCredentialsException;
import com.hogar360.users.users.domain.model.UserModel;
import com.hogar360.users.users.domain.ports.out.AuthenticationPersistencePort;
import com.hogar360.users.users.domain.ports.out.PasswordEncoderPort;
import com.hogar360.users.users.domain.ports.out.UserPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthenticationUseCaseTest {

    @Mock
    private UserPersistencePort userPersistencePort;

    @Mock
    private PasswordEncoderPort passwordEncoderPort;

    @Mock
    private AuthenticationPersistencePort authenticationPersistencePort;

    @InjectMocks
    private AuthenticationUseCase authenticationUseCase;

    private AuthenticationRequest authenticationRequest;
    private UserModel userModel;
    private final String rawPassword = "plainPassword123";
    private final String hashedPassword = "hashedPasswordXYZ";
    private final String generatedToken = "generatedJwtToken123";

    @BeforeEach
    void setUp() {

        authenticationRequest = new AuthenticationRequest("testuser@example.com", rawPassword);

        userModel = new UserModel();
        userModel.setId(1L);
        userModel.setEmail("testuser@example.com");
        userModel.setPassword(hashedPassword);
        userModel.setRole("USER");

    }

    @Test
    void testAuthenticate_successful() {
        when(userPersistencePort.getUserByEmail(authenticationRequest.email()))
                .thenReturn(userModel);

        when(passwordEncoderPort.matches(rawPassword, hashedPassword))
                .thenReturn(true);

        when(authenticationPersistencePort.generateToken(userModel))
                .thenReturn(generatedToken);


        AuthenticationResponse response = authenticationUseCase.authenticate(authenticationRequest);

        verify(userPersistencePort).getUserByEmail(authenticationRequest.email());
        verify(passwordEncoderPort).matches(rawPassword, hashedPassword);
        verify(authenticationPersistencePort).generateToken(userModel);

        assertNotNull(response);
        assertEquals(generatedToken, response.token());
    }

    @Test
    void testAuthenticate_userNotFound_throwsInvalidCredentialsException() {
        when(userPersistencePort.getUserByEmail(authenticationRequest.email()))
                .thenReturn(null); // User not found

        assertThrows(InvalidCredentialsException.class, () -> {
            authenticationUseCase.authenticate(authenticationRequest);
        });

        verify(userPersistencePort).getUserByEmail(authenticationRequest.email());
        verifyNoMoreInteractions(passwordEncoderPort, authenticationPersistencePort); // Ensure other mocks were not called
    }

    @Test
    void testAuthenticate_incorrectPassword_throwsInvalidCredentialsException() {

        when(userPersistencePort.getUserByEmail(authenticationRequest.email()))
                .thenReturn(userModel);

        when(passwordEncoderPort.matches(rawPassword, hashedPassword))
                .thenReturn(false);

        assertThrows(InvalidCredentialsException.class, () -> {
            authenticationUseCase.authenticate(authenticationRequest);
        });

        verify(userPersistencePort).getUserByEmail(authenticationRequest.email());
        verify(passwordEncoderPort).matches(rawPassword, hashedPassword);
        verifyNoMoreInteractions(authenticationPersistencePort);
    }
}