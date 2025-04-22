package com.hogar360.users.users.domain.usecases;

import com.hogar360.users.users.domain.exceptions.*;
import com.hogar360.users.users.domain.model.UserModel;
import com.hogar360.users.users.domain.ports.out.PasswordEncoderPort;
import com.hogar360.users.users.domain.ports.out.UserPersistencePort;
import com.hogar360.users.users.domain.utils.DomainConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserUseCaseTest {

    @Mock
    private UserPersistencePort userPersistencePort;

    @Mock
    private PasswordEncoderPort passwordEncoderPort;

    @InjectMocks
    private UserUseCase userUseCase;

    private UserModel validUserModel;

    private static final String ROLE_ADMIN = DomainConstants.ROLE_ADMIN;
    private static final String ROLE_USER = "USER";

    @BeforeEach
    void setUp() {
        validUserModel = new UserModel();
        validUserModel.setFirstName("John");
        validUserModel.setLastName("Doe");
        validUserModel.setIdentityDocument("123456789");
        validUserModel.setPhoneNumber("3101234567");
        validUserModel.setBirthDate(LocalDate.now().minusYears(20));
        validUserModel.setEmail("john.doe@example.com");
        validUserModel.setPassword("password123");
    }

    @Test
    void registerUser_WithAdminRole_ShouldProceedWithRegistration() {
        when(userPersistencePort.getUserByEmail(validUserModel.getEmail())).thenReturn(null);
        when(passwordEncoderPort.encode(validUserModel.getPassword())).thenReturn("encryptedPassword");

        userUseCase.registerUser(validUserModel, ROLE_ADMIN);

        verify(passwordEncoderPort).encode("password123");
        verify(userPersistencePort).getUserByEmail(validUserModel.getEmail());
        verify(userPersistencePort).saveUser(any(UserModel.class));
    }

    @Test
    void registerUser_WithNonAdminRole_ShouldThrowForbiddenExceptionAndNotSaveUser() {
        assertThrows(ForbiddenException.class, () -> userUseCase.registerUser(validUserModel, ROLE_USER));

        verify(userPersistencePort, never()).saveUser(any(UserModel.class));
        verify(passwordEncoderPort, never()).encode(anyString());
        verify(userPersistencePort, never()).getUserByEmail(anyString());
    }

    @Test
    void registerUser_ValidUserWithAdminRole_ShouldSaveUserWithEncryptedPasswordAndDefaultRole() {
        when(userPersistencePort.getUserByEmail(validUserModel.getEmail())).thenReturn(null);
        when(passwordEncoderPort.encode(validUserModel.getPassword())).thenReturn("encryptedPassword");

        userUseCase.registerUser(validUserModel, ROLE_ADMIN);

        ArgumentCaptor<UserModel> userCaptor = ArgumentCaptor.forClass(UserModel.class);
        verify(userPersistencePort, times(1)).saveUser(userCaptor.capture());
        UserModel savedUser = userCaptor.getValue();

        assertEquals("encryptedPassword", savedUser.getPassword());
        assertEquals(DomainConstants.DEFAULT_USER_ROLE, savedUser.getRole());
        verify(passwordEncoderPort).encode("password123");
        verify(userPersistencePort).getUserByEmail(validUserModel.getEmail());
    }

    @Test
    void registerUser_MissingFirstName_ShouldThrowNullPointerException() {
        validUserModel.setFirstName(null);

        NullPointerException exception = assertThrows(NullPointerException.class,
                () -> userUseCase.registerUser(validUserModel, ROLE_ADMIN));

        assertEquals(DomainConstants.ERROR_REQUIRED_FIRSTNAME, exception.getMessage());
        verify(userPersistencePort, never()).getUserByEmail(anyString());
    }

    @Test
    void registerUser_MissingLastName_ShouldThrowNullPointerException() {
        validUserModel.setLastName(null);

        NullPointerException exception = assertThrows(NullPointerException.class,
                () -> userUseCase.registerUser(validUserModel, ROLE_ADMIN));

        assertEquals(DomainConstants.ERROR_REQUIRED_LASTNAME, exception.getMessage());
        verify(userPersistencePort, never()).getUserByEmail(anyString());
    }

    @Test
    void registerUser_MissingDocument_ShouldThrowNullPointerException() {
        validUserModel.setIdentityDocument(null);

        NullPointerException exception = assertThrows(NullPointerException.class,
                () -> userUseCase.registerUser(validUserModel, ROLE_ADMIN));

        assertEquals(DomainConstants.ERROR_REQUIRED_DOCUMENT, exception.getMessage());
        verify(userPersistencePort, never()).getUserByEmail(anyString());
    }

    @Test
    void registerUser_MissingPhone_ShouldThrowNullPointerException() {
        validUserModel.setPhoneNumber(null);

        NullPointerException exception = assertThrows(NullPointerException.class,
                () -> userUseCase.registerUser(validUserModel, ROLE_ADMIN));

        assertEquals(DomainConstants.ERROR_REQUIRED_PHONE, exception.getMessage());
        verify(userPersistencePort, never()).getUserByEmail(anyString());
    }

    @Test
    void registerUser_MissingBirthDate_ShouldThrowNullPointerException() {
        validUserModel.setBirthDate(null);

        NullPointerException exception = assertThrows(NullPointerException.class,
                () -> userUseCase.registerUser(validUserModel, ROLE_ADMIN));

        assertEquals(DomainConstants.ERROR_REQUIRED_BIRTHDATE, exception.getMessage());
        verify(userPersistencePort, never()).getUserByEmail(anyString());
    }

    @Test
    void registerUser_MissingEmail_ShouldThrowNullPointerException() {
        validUserModel.setEmail(null);

        NullPointerException exception = assertThrows(NullPointerException.class,
                () -> userUseCase.registerUser(validUserModel, ROLE_ADMIN));

        assertEquals(DomainConstants.ERROR_REQUIRED_EMAIL, exception.getMessage());
        verify(userPersistencePort, never()).getUserByEmail(anyString());
    }

    @Test
    void registerUser_MissingPassword_ShouldThrowNullPointerException() {
        validUserModel.setPassword(null);

        NullPointerException exception = assertThrows(NullPointerException.class,
                () -> userUseCase.registerUser(validUserModel, ROLE_ADMIN));

        assertEquals(DomainConstants.ERROR_REQUIRED_PASSWORD, exception.getMessage());
        verify(userPersistencePort, never()).getUserByEmail(anyString());
    }

    @Test
    void registerUser_InvalidEmailFormat_ShouldThrowInvalidEmailException() {
        validUserModel.setEmail("invalid-email");

        assertThrows(InvalidEmailException.class,
                () -> userUseCase.registerUser(validUserModel, ROLE_ADMIN));

        verify(userPersistencePort, never()).getUserByEmail(anyString());
    }

    @Test
    void registerUser_InvalidPhoneFormat_ShouldThrowInvalidPhoneException() {
        validUserModel.setPhoneNumber("invalid-phone");

        assertThrows(InvalidPhoneException.class,
                () -> userUseCase.registerUser(validUserModel, ROLE_ADMIN));

        verify(userPersistencePort, never()).getUserByEmail(anyString());
    }

    @Test
    void registerUser_PhoneTooLong_ShouldThrowInvalidPhoneException() {
        validUserModel.setPhoneNumber("12345678901234567890");

        assertThrows(InvalidPhoneException.class,
                () -> userUseCase.registerUser(validUserModel, ROLE_ADMIN));

        verify(userPersistencePort, never()).getUserByEmail(anyString());
    }

    @Test
    void registerUser_InvalidDocumentFormat_ShouldThrowInvalidDocumentException() {
        validUserModel.setIdentityDocument("invalid-document");

        assertThrows(InvalidDocumentException.class,
                () -> userUseCase.registerUser(validUserModel, ROLE_ADMIN));

        verify(userPersistencePort, never()).getUserByEmail(anyString());
    }

    @Test
    void registerUser_UnderAgeUser_ShouldThrowUnderAgeUserException() {
        validUserModel.setBirthDate(LocalDate.now().minusYears(DomainConstants.MIN_AGE - 1));

        assertThrows(UnderAgeUserException.class,
                () -> userUseCase.registerUser(validUserModel, ROLE_ADMIN));

        verify(userPersistencePort, never()).getUserByEmail(anyString());
    }

    @Test
    void registerUser_UserAlreadyExists_ShouldThrowUserAlreadyExistsException() {
        when(userPersistencePort.getUserByEmail(validUserModel.getEmail())).thenReturn(new UserModel());

        assertThrows(UserAlreadyExistsException.class,
                () -> userUseCase.registerUser(validUserModel, ROLE_ADMIN));

        verify(userPersistencePort).getUserByEmail(validUserModel.getEmail());
        verify(userPersistencePort, never()).saveUser(any(UserModel.class));
    }
}