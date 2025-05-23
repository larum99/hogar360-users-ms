package com.hogar360.users.users.domain.usecases;

import com.hogar360.users.users.domain.exceptions.*;
import com.hogar360.users.users.domain.model.UserModel;
import com.hogar360.users.users.domain.ports.in.UserServicePort;
import com.hogar360.users.users.domain.ports.out.PasswordEncoderPort;
import com.hogar360.users.users.domain.ports.out.UserPersistencePort;
import com.hogar360.users.users.domain.utils.DomainConstants;
import com.hogar360.users.users.domain.utils.RegexUtils;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public class UserUseCase implements UserServicePort {
    private final UserPersistencePort userPersistencePort;
    private final PasswordEncoderPort passwordEncoderPort;

    public UserUseCase(UserPersistencePort userPersistencePort, PasswordEncoderPort passwordEncoderPort) {
        this.userPersistencePort = userPersistencePort;
        this.passwordEncoderPort = passwordEncoderPort;
    }

    @Override
    public void registerUser(UserModel userModel, String role) {
        validateRole(role);
        validateMandatoryFields(userModel);
        validateEmail(userModel.getEmail());
        validatePhone(userModel.getPhoneNumber());
        validateDocument(userModel.getIdentityDocument());
        validateAge(userModel.getBirthDate());

        checkIfDocumentAlreadyExists(userModel.getIdentityDocument());

        checkIfUserAlreadyExists(userModel.getEmail());

        String encryptedPassword = passwordEncoderPort.encode(userModel.getPassword());
        userModel.setPassword(encryptedPassword);

        userModel.setRole(DomainConstants.DEFAULT_USER_ROLE);

        userPersistencePort.saveUser(userModel);
    }

    private void validateRole(String role) {
        if (!DomainConstants.ROLE_ADMIN.equals(role)) {
            throw new ForbiddenException();
        }
    }

    private void validateMandatoryFields(UserModel userModel) {
        Objects.requireNonNull(userModel.getFirstName(), DomainConstants.ERROR_REQUIRED_FIRSTNAME);
        Objects.requireNonNull(userModel.getLastName(), DomainConstants.ERROR_REQUIRED_LASTNAME);
        Objects.requireNonNull(userModel.getIdentityDocument(), DomainConstants.ERROR_REQUIRED_DOCUMENT);
        Objects.requireNonNull(userModel.getPhoneNumber(), DomainConstants.ERROR_REQUIRED_PHONE);
        Objects.requireNonNull(userModel.getBirthDate(), DomainConstants.ERROR_REQUIRED_BIRTHDATE);
        Objects.requireNonNull(userModel.getEmail(), DomainConstants.ERROR_REQUIRED_EMAIL);
        Objects.requireNonNull(userModel.getPassword(), DomainConstants.ERROR_REQUIRED_PASSWORD);
    }

    private void validateEmail(String email) {
        if (!RegexUtils.EMAIL_PATTERN.matcher(email).matches()) {
            throw new InvalidEmailException();
        }
    }

    private void validatePhone(String phone) {
        if (phone.length() > DomainConstants.PHONE_MAX_LENGTH ||
                !RegexUtils.PHONE_PATTERN.matcher(phone).matches()) {
            throw new InvalidPhoneException();
        }
    }

    private void validateDocument(String document) {
        if (!RegexUtils.DOCUMENT_PATTERN.matcher(document).matches()) {
            throw new InvalidDocumentException();
        }
    }

    private void validateAge(LocalDate birthDate) {
        int age = Period.between(birthDate, LocalDate.now()).getYears();
        if (age < DomainConstants.MIN_AGE) {
            throw new UnderAgeUserException();
        }
    }

    private void checkIfDocumentAlreadyExists(String identityDocument) {
        UserModel existingUser = userPersistencePort.getUserByDocument(identityDocument);
        if (existingUser != null) {
            throw new DuplicateDocumentException();
        }
    }

    private void checkIfUserAlreadyExists(String email) {
        UserModel existingUser = userPersistencePort.getUserByEmail(email);
        if (existingUser != null) {
            throw new UserAlreadyExistsException();
        }
    }

}
