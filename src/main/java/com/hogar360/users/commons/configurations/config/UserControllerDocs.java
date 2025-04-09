package com.hogar360.users.commons.configurations.config;

import com.hogar360.users.users.application.dto.request.SaveUserRequest;
import com.hogar360.users.users.application.dto.response.SaveUserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class UserControllerDocs {

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
            summary = "Guardar(crear) un usuario",
            description = "Crear un nuevo usuario con sus credenciales en el sistema.",
            requestBody = @RequestBody(
                    description = "Datos del usuario",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = SaveUserRequest.class),
                            examples = @ExampleObject(
                                    name = "Ejemplo de usuario",
                                    summary = "Ejemplo de creación de usuario",
                                    description = "Petición para crear un usuario",
                                    value = SwaggerExamples.CREATE_USER_REQUEST
                            )
                    )
            )
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = SaveUserResponse.class),
                            examples = @ExampleObject(
                                    name = "Respuesta exitosa",
                                    summary = "Respuesta de creación de usuario",
                                    description = "Respuesta con usuario recién creado",
                                    value = SwaggerExamples.USER_CREATED_RESPONSE
                            )
                    )
            ),
            @ApiResponse(responseCode = "400", description = "User already exists or invalid data",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Error de validación",
                                    summary = "El email ya existe",
                                    description = "Se intenta crear una categoría con un email duplicado",
                                    value = SwaggerExamples.USER_ALREADY_EXISTS_RESPONSE
                            )
                    )
            )
    })
    public @interface CreateUserDocs {
    }

}
