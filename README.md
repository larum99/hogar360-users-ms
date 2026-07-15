# Hogar360 - Microservicio de Usuarios

Microservicio encargado de gestionar la autenticacion, registro y consulta de usuarios dentro de la plataforma **Hogar360**. Permite a administradores registrar nuevos vendedores, autenticar credenciales y generar tokens JWT para el acceso seguro a los demas microservicios.

## Descripcion

Este microservicio hace parte de una arquitectura de microservicios y es consumido por el front-end Angular de Hogar360, ademas de otros microservicios como el [Microservicio de Casas](https://github.com) (`houses-ms`) y el [Microservicio de Visitas](https://github.com) (`visits-ms`) para validar usuarios y roles via tokens JWT.

**Funcionalidades principales:**

- Registro de usuarios nuevos (solo admin)
- Autenticacion de credenciales (email + password) con generacion de JWT
- Consulta de usuario por ID (publico)
- Validacion de roles via tokens JWT (ADMIN, VENDEDOR)
- Encriptacion segura de contrasenas con BCrypt

## Stack Tecnologico

| Componente         | Tecnologia                |
| ------------------ | ------------------------- |
| Lenguaje           | Java 17                   |
| Framework          | Spring Boot 3.4.4         |
| Arquitectura       | Hexagonal (Puertos y Adaptadores) |
| Base de datos      | MySQL 8                   |
| ORM                | Spring Data JPA / Hibernate |
| Seguridad          | Spring Security + JWT (jjwt 0.11.5) |
| Encriptacion       | BCrypt                    |
| Mapeo de Objetos   | MapStruct 1.6.3           |
| Construccion       | Gradle                    |
| Documentacion API  | SpringDoc OpenAPI 2.8.5   |
| Pruebas            | JUnit 5 + Mockito 5.11.0  |
| Cobertura          | JaCoCo 0.8.8              |

## Arquitectura

El proyecto sigue el patron de **Arquitectura Hexagonal** con las siguientes capas:

```
src/main/java/com/hogar360/users/
├── commons/configurations/            ← Configuracion compartida
│   ├── beans/                         ← Inyeccion manual de dependencias (BeanConfiguration)
│   ├── config/                        ← Constantes, docs Swagger, OpenAPI
│   └── utils/                         ← Constantes generales, utilidad de encriptacion
│
└── users/
    ├── domain/                        ← Logica de negocio pura (sin dependencias de framework)
    │   ├── model/                     ← Modelo de dominio (UserModel)
    │   ├── usecases/                  ← Casos de uso (UserUseCase, AuthenticationUseCase)
    │   ├── ports/
    │   │   ├── in/                    ← Puertos de entrada (UserServicePort, AuthenticationServicePort, RoleValidatorPort)
    │   │   └── out/                   ← Puertos de salida (UserPersistencePort, AuthenticationPersistencePort, PasswordEncoderPort)
    │   ├── exceptions/                ← Excepciones de dominio (~9 clases)
    │   └── utils/                     ← Constantes de dominio, patrones regex
    │
    ├── application/                   ← Orquestacion y capa de servicios
    │   ├── services/                  ← Interfaces de servicio
    │   │   └── impl/                  ← Implementaciones de servicios
    │   ├── dto/
    │   │   ├── request/               ← DTOs de entrada (records)
    │   │   └── response/              ← DTOs de salida (records)
    │   └── mappers/                   ← Mappers MapStruct (DTO <-> Dominio)
    │
    └── infrastructure/                ← Implementaciones framework-especificas
        ├── endpoints/rest/            ← Controladores REST (AuthController, UserController)
        ├── entities/                  ← Entidades JPA (UserEntity)
        ├── repositories/mysql/        ← Repositorios Spring Data
        ├── adapters/
        │   ├── persistence/           ← Adaptadores de persistencia y JWT
        │   └── encoders/              ← Adaptador BCrypt (PasswordEncoderAdapter)
        ├── mappers/                   ← Mappers MapStruct (Entidad <-> Dominio)
        ├── security/                  ← Filtros JWT, configuracion de seguridad, utilidades
        └── exceptionshandlers/        ← Manejo global de excepciones (ControllerAdvisor)
```

## Prerequisitos

- Java 17 o superior
- Gradle (incluido via wrapper)
- MySQL 8 corriendo en `localhost:3306`
- Base de datos `hogar360_users` creada

## Inicio Rapido

1. **Clonar el repositorio:**

```bash
git clone https://github.com/tu-usuario/hogar360-users-ms.git
cd hogar360-users-ms
```

2. **Configurar las variables de entorno** (ver seccion siguiente).

3. **Crear la base de datos en MySQL:**

```sql
CREATE DATABASE hogar360_users;
```

4. **Ejecutar la aplicacion:**

```bash
# Windows
gradlew.bat bootRun

# Linux / macOS
./gradlew bootRun
```

La aplicacion estara disponible en `http://localhost:8091`.

## Variables de Entorno

| Variable                | Descripcion                                  | Ejemplo                        |
| ----------------------- | -------------------------------------------- | ------------------------------ |
| `DB_USER`               | Usuario de MySQL                             | `root`                         |
| `DB_PASSWORD`           | Contrasena de MySQL                          | `password123`                  |
| `JWT_SECRET_KEY`        | Clave HMAC para firmar y verificar tokens JWT| `mySecretKey1234567890...`     |
| `JWT_EXPIRATION_MILLIS` | Tiempo de expiracion del token en milisegundos| `180000` (3 minutos)          |

## Endpoints API

### Autenticacion

| Metodo | Ruta                                       | Autenticacion   | Descripcion                                      |
| ------ | ------------------------------------------ | --------------- | ------------------------------------------------ |
| `POST`   | `/api/v1/users/auth/login`               | Publico         | Autenticar usuario y obtener token JWT           |

### Usuarios

| Metodo | Ruta                                       | Autenticacion   | Descripcion                                      |
| ------ | ------------------------------------------ | --------------- | ------------------------------------------------ |
| `POST`   | `/api/v1/users/`                          | JWT (ADMIN)     | Registrar un nuevo usuario                       |
| `GET`    | `/api/v1/users/{id}`                      | Publico         | Obtener usuario por ID                           |

### Ejemplo: Registrar usuario

```http
POST /api/v1/users/
Content-Type: application/json
Authorization: Bearer <token>

{
  "firstName": "Juan",
  "lastName": "Perez",
  "identityDocument": "1098765432",
  "phoneNumber": "+573001234567",
  "birthDate": "1990-05-15",
  "email": "juan.perez@correo.com",
  "password": "MiClaveSegura123"
}
```

### Ejemplo: Autenticar usuario

```http
POST /api/v1/users/auth/login
Content-Type: application/json

{
  "email": "juan.perez@correo.com",
  "password": "MiClaveSegura123"
}
```

### Ejemplo: Obtener usuario por ID

```http
GET /api/v1/users/1
```

## Estructura del Proyecto

```
hogar360-users-ms/
├── build.gradle                          # Configuracion de dependencias y plugins
├── settings.gradle                       # Nombre del proyecto
├── gradlew / gradlew.bat                 # Gradle wrapper
├── src/
│   ├── main/
│   │   ├── java/com/hogar360/users/
│   │   │   ├── UsersApplication.java
│   │   │   ├── commons/configurations/   # Configuracion de beans, Swagger y constantes
│   │   │   └── users/
│   │   │       ├── domain/               # Modelo, casos de uso, puertos, excepciones
│   │   │       ├── application/          # Servicios, DTOs, mappers
│   │   │       └── infrastructure/       # Controladores, entidades, repositorios, adaptadores, seguridad
│   │   └── resources/
│   │       └── application.yml           # Configuracion de la aplicacion
│   └── test/
│       └── java/com/hogar360/users/
│           └── users/domain/usecases/    # Pruebas unitarias de casos de uso
```

## Ejecutar Pruebas

```bash
# Ejecutar todas las pruebas
gradlew.bat test

# Ejecutar pruebas con reporte de cobertura
gradlew.bat test jacocoTestReport
```

Los reportes de cobertura se generan en:
- HTML: `build/reports/jacoco/test/html/index.html`
- XML: `build/reports/jacoco/test/jacocoTestReport.xml`

## Documentacion API

Una vez ejecutada la aplicacion, la documentacion interactiva esta disponible en:

- **Swagger UI:** `http://localhost:8091/swagger-ui/index.html`
- **OpenAPI Docs:** `http://localhost:8091/api-docs`

## Reglas de Negocio

- Solo usuarios con rol **ADMIN** pueden registrar nuevos usuarios.
- Todos los usuarios nuevos se crean con rol **VENDEDOR** por defecto (independientemente del rol enviado en el request).
- El documento de identidad debe contener **solo digitos** y ser unico en el sistema.
- El email debe tener un formato valido y ser unico en el sistema.
- El numero de telefono debe tener entre **7 y 15 digitos**, con opcion de prefijo `+` (maximo 13 caracteres).
- El usuario debe ser mayor o igual a **18 anios**.
- Las contrasenas se almacenan encriptadas con **BCrypt**.
- Los tokens JWT incluyen los claims `email`, `role` e `id` y expiran segun la configuracion (`JWT_EXPIRATION_MILLIS`).
- La contrasena es **obligatoria** en el registro (campos requeridos: firstName, lastName, identityDocument, phoneNumber, birthDate, email, password).

## Licencia

Este proyecto es privado. Todos los derechos reservados.
