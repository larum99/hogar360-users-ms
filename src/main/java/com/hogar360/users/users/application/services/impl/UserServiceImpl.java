package com.hogar360.users.users.application.services.impl;

import com.hogar360.users.commons.configurations.utils.Constants;
import com.hogar360.users.users.application.dto.request.SaveUserRequest;
import com.hogar360.users.users.application.dto.response.SaveUserResponse;
import com.hogar360.users.users.application.mappers.UserDtoMapper;
import com.hogar360.users.users.application.services.UserService;
import com.hogar360.users.users.domain.ports.in.UserServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserServicePort userServicePort;
    private final UserDtoMapper userDtoMapper;

    @Override
    public SaveUserResponse save(SaveUserRequest request) {
        userServicePort.registerUser(userDtoMapper.requestToModel(request));
        return new SaveUserResponse(Constants.SAVE_USER_RESPONSE_MESSAGE, LocalDateTime.now());
    }
}
