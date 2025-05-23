package com.hogar360.users.users.domain.ports.out;

import com.hogar360.users.users.domain.model.UserModel;

public interface UserPersistencePort {
    void saveUser(UserModel userModel);
    UserModel getUserByEmail(String email);
    UserModel getUserByDocument(String identityDocument);
}
