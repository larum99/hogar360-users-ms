package com.hogar360.users.users.application.services;

import com.hogar360.users.users.application.dto.request.SaveUserRequest;
import com.hogar360.users.users.application.dto.response.SaveUserResponse;
import com.hogar360.users.users.application.dto.response.UserSimpleResponse;

public interface UserService {
    SaveUserResponse save(SaveUserRequest request, String token);
    UserSimpleResponse getUserById(Long id);
}
