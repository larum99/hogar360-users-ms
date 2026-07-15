package com.hogar360.users.users.infrastructure.endpoints.rest;

import com.hogar360.users.commons.configurations.config.ControllerConstants;
import com.hogar360.users.commons.configurations.config.UserControllerDocs.CreateUserDocs;
import com.hogar360.users.users.application.dto.request.SaveUserRequest;
import com.hogar360.users.users.application.dto.response.SaveUserResponse;
import com.hogar360.users.users.application.dto.response.UserSimpleResponse;
import com.hogar360.users.users.application.services.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ControllerConstants.BASE_URL)

@RequiredArgsConstructor
@Tag(name = ControllerConstants.TAG, description = ControllerConstants.TAG_DESCRIPTION)
public class UserController {

    private final UserService userService;

    @CreateUserDocs
    @PreAuthorize(ControllerConstants.ROLE_ADMIN)
    @PostMapping(ControllerConstants.SAVE_PATH)
    public ResponseEntity<SaveUserResponse> saveUser(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader,
            @RequestBody SaveUserRequest request) {
        String token = authorizationHeader.replace(ControllerConstants.BEARER_PREFIX, "");

        SaveUserResponse response = userService.save(request, token);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping(ControllerConstants.GET_USER_BY_ID_PATH)
    public ResponseEntity<UserSimpleResponse> getUserById(@PathVariable Long id) {
        UserSimpleResponse user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
