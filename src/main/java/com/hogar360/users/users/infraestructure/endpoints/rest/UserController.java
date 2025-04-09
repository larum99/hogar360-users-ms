package com.hogar360.users.users.infraestructure.endpoints.rest;

import com.hogar360.users.commons.configurations.config.UserControllerDocs.CreateUserDocs;
import com.hogar360.users.users.application.dto.request.SaveUserRequest;
import com.hogar360.users.users.application.dto.response.SaveUserResponse;
import com.hogar360.users.users.application.services.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Tag(name = "Usuarios", description = "Operaciones relacionadas con los usuarios")
public class UserController {

    private final UserService userService;

    @CreateUserDocs
    @PostMapping("/")
    public ResponseEntity<SaveUserResponse> saveUser(@RequestBody SaveUserRequest request) {
        SaveUserResponse response = userService.save(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
