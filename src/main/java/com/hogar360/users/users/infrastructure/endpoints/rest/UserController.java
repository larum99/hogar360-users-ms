package com.hogar360.users.users.infrastructure.endpoints.rest;

import com.hogar360.users.commons.configurations.config.ControllerConstants;
import com.hogar360.users.commons.configurations.config.UserControllerDocs.CreateUserDocs;
import com.hogar360.users.users.application.dto.request.SaveUserRequest;
import com.hogar360.users.users.application.dto.response.SaveUserResponse;
import com.hogar360.users.users.application.services.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ControllerConstants.BASE_URL)

@RequiredArgsConstructor
@Tag(name = ControllerConstants.TAG, description = ControllerConstants.TAG_DESCRIPTION)
public class UserController {

    private final UserService userService;

    @CreateUserDocs
    @PostMapping(ControllerConstants.SAVE_PATH)
    public ResponseEntity<SaveUserResponse> saveUser(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader,
            @RequestBody SaveUserRequest request) {
        String token = authorizationHeader.replace("Bearer ", "");

        SaveUserResponse response = userService.save(request, token);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
