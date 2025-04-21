package com.hogar360.users.users.domain.ports.out;

import com.hogar360.users.users.domain.model.UserModel;

public interface AuthenticationPersistencePort {
    String generateToken(UserModel user);
}