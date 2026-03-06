# Voll.med API

REST API for managing doctors, patients, and medical appointments, with JWT authentication and Swagger/OpenAPI documentation.

## Tech Stack

- Java 25
- Spring Boot 4 (snapshot)
- Spring Security (JWT)
- Spring Data JPA
- Flyway
- MySQL
- Springdoc OpenAPI + Swagger UI
- Gradle

## Prerequisites

- JDK 25
- MySQL 8+
- Gradle (or use the included wrapper)

## Configuration

The application uses environment variables/system properties for database and JWT setup:

- DB_USERNAME
- DB_PASSWORD
- DB_NAME
- DB_URL (example: localhost:3306)
- JWT_TOKEN_SECRETY

> Note: The project currently reads `JWT_TOKEN_SECRET` or `JWT_TOKEN_SECRETY`.

## Running in Development

```bash
./gradlew bootRun
```

## Building the JAR

```bash
./gradlew clean build
```

Generated artifact (default):

```bash
build/libs/api-0.0.1-SNAPSHOT.jar
```

## Running the JAR with Production Profile

Use this command exactly:

```bash
java -Dspring.profiles.active=prod -DDB_USERNAME=root -DDB_PASSWORD=secret  -DDB_NAME=volmed_api -DDB_URL=localhost:3306 -DJWT_TOKEN_SECRETY=yoursecret  -jar ./api-0.0.1-SNAPSHOT.jar
```

If needed, run from the folder containing the JAR or adjust the path, for example:

```bash
java -Dspring.profiles.active=prod -DDB_USERNAME=root -DDB_PASSWORD=secret -DDB_NAME=volmed_api -DDB_URL=localhost:3306 -DJWT_TOKEN_SECRETY=yoursecret -jar build/libs/api-0.0.1-SNAPSHOT.jar
```

## Swagger / OpenAPI

After starting the app (default port 8080):

- Swagger UI: http://localhost:8080/swagger-ui/index.html
- OpenAPI JSON: http://localhost:8080/v3/api-docs

### Authentication in Swagger

1. Call POST /login with valid credentials.
2. Copy the returned JWT token.
3. In Swagger UI, click Authorize and paste the token as Bearer token.

## API Routes (as shown in Swagger)

### Auth

- POST /login

### Doctors (JWT required)

- POST /doctors
- GET /doctors
- GET /doctors/{id}
- PUT /doctors
- DELETE /doctors/{id}

### Patients (JWT required)

- POST /patients
- GET /patients
- GET /patients/{id}
- PUT /patients
- DELETE /patients/{id}

### Appointments (JWT required)

- POST /appointments
- DELETE /appointments

## Notes

- The endpoint POST /login and Swagger endpoints are public.
- All other endpoints require authentication.
- Flyway migrations run automatically on startup.
