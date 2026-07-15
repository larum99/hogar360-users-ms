package com.hogar360.users.users.domain.ports.in;

import com.hogar360.users.users.domain.model.UserModel;

public interface UserServicePort {
    void registerUser(UserModel userModel, String role);
    UserModel getUserById(Long id);
}
