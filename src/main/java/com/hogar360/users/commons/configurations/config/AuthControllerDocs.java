package com.hogar360.users.commons.configurations.config;

import com.hogar360.users.users.application.dto.request.AuthenticationRequest;
import com.hogar360.users.users.application.dto.response.AuthenticationResponse;
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

public class AuthControllerDocs {

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
            summary = "Autenticación de usuarios",
            description = "Permite iniciar sesión a un usuario registrado para obtener su token JWT.",
            requestBody = @RequestBody(
                    description = "Credenciales del usuario",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AuthenticationRequest.class),
                            examples = @ExampleObject(
                                    name = "Ejemplo de login",
                                    summary = "Login exitoso",
                                    description = "Petición de login enviando correo y contraseña",
                                    value = SwaggerExamples.AUTHENTICATION_REQUEST
                            )
                    )
            )
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login exitoso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AuthenticationResponse.class),
                            examples = @ExampleObject(
                                    name = "Token generado",
                                    summary = "Respuesta de login",
                                    description = "Token JWT generado tras autenticarse",
                                    value = SwaggerExamples.AUTHENTICATION_RESPONSE
                            )
                    )
            ),
            @ApiResponse(responseCode = "401", description = "Credenciales inválidas",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Error de autenticación",
                                    summary = "Login fallido",
                                    description = "Credenciales incorrectas",
                                    value = SwaggerExamples.AUTHENTICATION_ERROR_RESPONSE
                            )
                    )
            )
    })
    public @interface LoginDocs {
    }

}
