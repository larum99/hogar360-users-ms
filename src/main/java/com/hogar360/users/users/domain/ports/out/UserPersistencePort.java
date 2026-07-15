package com.hogar360.users.users.domain.ports.out;

import com.hogar360.users.users.domain.model.UserModel;

import java.util.Optional;

public interface UserPersistencePort {
    void saveUser(UserModel userModel);
    Optional<UserModel> getUserByEmail(String email);

    Optional<UserModel> getUserByDocument(String identityDocument);
    Optional<UserModel> getUserById(Long id);
}
