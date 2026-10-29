# Secure Java REST API — Raheena Shaik

A Spring Boot REST API demonstrating user registration, login, BCrypt password hashing, signed JWT bearer tokens, protected endpoints, validation, and MySQL persistence.

## Stack
- Java 17, Spring Boot 3.4.5, Maven
- Spring Web, Spring Security, Spring Data JPA, Bean Validation
- MySQL 8.4
- JJWT 0.12.6

## Prerequisites
Install Java 17+, Maven 3.9+, and Docker Desktop (or a local MySQL 8 instance).

## Run locally
1. Open a terminal in the project folder.
2. Start the database:
   ```bash
   docker compose up -d
   ```
3. Set a strong JWT secret (PowerShell example):
   ```powershell
   $env:APP_JWT_SECRET = "replace-with-a-random-secret-at-least-32-characters"
   ```
   macOS/Linux:
   ```bash
   export APP_JWT_SECRET='replace-with-a-random-secret-at-least-32-characters'
   ```
4. Run the app:
   ```bash
   mvn spring-boot:run
   ```
5. API base URL: `http://localhost:8080`

The sample database password is for local development only. Change all credentials before deploying. Never commit real secrets.

## API endpoints

### 1) Register — public
`POST /api/auth/register`
```json
{
  "name": "Raheena Shaik",
  "email": "raheena@example.com",
  "password": "StrongPass123!"
}
```
Returns `201 Created` and a user profile. Password hashes are never returned.

### 2) Login — public
`POST /api/auth/login`
```json
{
  "email": "raheena@example.com",
  "password": "StrongPass123!"
}
```
Returns a bearer token:
```json
{
  "tokenType": "Bearer",
  "accessToken": "<JWT>",
  "expiresInSeconds": 3600
}
```

### 3) Get current user — protected
`GET /api/users/me`
Header:
`Authorization: Bearer <JWT>`

Returns the authenticated user's ID, name, and email. Without a valid token, the endpoint returns `401 Unauthorized`.

## Test with curl

Register:
```bash
curl -i -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{"name":"Raheena Shaik","email":"raheena@example.com","password":"StrongPass123!"}'
```

Login:
```bash
curl -i -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email":"raheena@example.com","password":"StrongPass123!"}'
```

Copy `accessToken` from the login response, then:
```bash
curl -i http://localhost:8080/api/users/me \
  -H "Authorization: Bearer PASTE_TOKEN_HERE"
```

## Security notes
- Passwords are stored as BCrypt hashes.
- JWTs are signed and expire after one hour by default.
- All endpoints except registration, login, and health are authenticated.
- The application is stateless; no server-side session is used.
- Request bodies are validated; duplicate email addresses return `409 Conflict`.
- Do not use the sample database credentials in production.
- For production, use HTTPS, managed secrets, database migrations (Flyway/Liquibase), rate limiting, security monitoring, and a refresh-token/revocation strategy as required.
- This is an internship demonstration project; “production-ready” deployment still requires environment-specific hardening and tests.

## Database schema
`app_users`:
- `id`: generated primary key
- `name`: required, max 80 characters
- `email`: required, unique, max 190 characters
- `password_hash`: required BCrypt hash; never exposed through API responses

Hibernate creates/updates the schema for local development. Use versioned migrations rather than `ddl-auto=update` in production.
