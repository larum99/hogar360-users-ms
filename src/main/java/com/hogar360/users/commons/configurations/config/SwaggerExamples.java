package com.hogar360.users.commons.configurations.config;

public class SwaggerExamples {

    private SwaggerExamples() {
        throw new IllegalStateException("Utility class");
    }

    public static final String CREATE_USER_REQUEST = """
        {
          "firstName": "María",
          "lastName": "González",
          "identityDocument": "1098765432",
          "phoneNumber": "3001234567",
          "birthDate": "1992-08-15",
          "email": "maria.gonzalez@example.com",
          "password": "securePassword123",
          "role": "CLIENT"
        }
    """;

    public static final String USER_CREATED_RESPONSE = """
        {
          "message": "User created successfully.",
          "time": "2025-04-07T12:00:00"
        }
    """;

    public static final String USER_ALREADY_EXISTS_RESPONSE = """
        {
          "message": "User already exists.",
          "time": "2025-04-07T12:01:00"
        }
    """;

    public static final String AUTHENTICATION_REQUEST = """
    {
       "email": "Marian.perez@example.com",
       "password": "password123"
    }
""";

    public static final String AUTHENTICATION_RESPONSE = """
    {
      "token": "eyJhbGciOiJIUzI1NiJ9..."
    }
""";

    public static final String AUTHENTICATION_ERROR_RESPONSE = """
    {
      "message": "Invalid email or password.",
      "time": "2025-04-07T12:10:00"
    }
""";

}

