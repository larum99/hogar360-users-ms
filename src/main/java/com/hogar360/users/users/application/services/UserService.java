package com.hogar360.users.users.application.services;

import com.hogar360.users.users.application.dto.request.SaveUserRequest;
import com.hogar360.users.users.application.dto.response.SaveUserResponse;

public interface UserService {
    SaveUserResponse save(SaveUserRequest request);
}
