# Secure REST API with JWT Authentication

A secure and scalable REST API built using **Java, Spring Boot, Spring Security, JWT, and MySQL**. This project demonstrates secure user authentication, password encryption, protected endpoints, and database integration following standard backend development practices.

## Overview

This project implements a RESTful backend service with user registration and login functionality. It uses JSON Web Tokens (JWT) to authenticate users and protect API endpoints, while Spring Security manages access control and BCrypt securely hashes user passwords.

## Features

- **User Registration** — Register users with validated credentials.
- **Secure Login** — Authenticate users using their credentials.
- **Password Hashing** — Store passwords securely using BCrypt.
- **JWT Authentication** — Generate and validate tokens for authenticated requests.
- **Protected Endpoints** — Restrict access to authenticated users.
- **MySQL Integration** — Persist user data in a relational database.
- **RESTful API Design** — Organize backend functionality through HTTP endpoints.
- **Configuration Management** — Use environment variables for sensitive configuration.

## Technology Stack

- **Language:** Java 21
- **Framework:** Spring Boot
- **Security:** Spring Security, JWT
- **Database:** MySQL
- **Build Tool:** Apache Maven
- **ORM:** Spring Data JPA / Hibernate
- **Password Hashing:** BCrypt

## Prerequisites

Before running the application, install:

- Java 21 or compatible JDK
- Apache Maven
- MySQL Server

## Getting Started

1. Clone the repository:

   ```bash
   git clone https://github.com/shaikraheena1337-code/java-rest-api-secure-auth.git
   cd java-rest-api-secure-auth
   ```

2. Create the MySQL database and configure the database connection using environment variables.

3. Configure the following environment variables:

   - `DB_URL` — MySQL connection URL
   - `DB_USERNAME` — Database username
   - `DB_PASSWORD` — Database password
   - `APP_JWT_SECRET` — A strong, randomly generated secret of at least 32 bytes
   - `JWT_EXPIRATION_SECONDS` — Token expiration time in seconds (optional; defaults to 3600)

4. Build the application:

   ```bash
   mvn clean package
   ```

5. Run the application:

   ```bash
   mvn spring-boot:run
   ```

The application runs on port `8080` by default.

## API Functionality

| Functionality | Description |
|---|---|
| User Registration | Creates a user account |
| User Login | Validates credentials and issues a JWT |
| Authenticated Access | Allows access to protected endpoints using a valid token |
| Database Operations | Stores and retrieves user information |

Use the API routes defined in the source code when testing requests with Postman or another API client.

## Security Practices

- Passwords are hashed using BCrypt rather than stored as plain text.
- JWTs are used to authenticate requests to protected endpoints.
- Sensitive configuration is supplied through environment variables.
- Database access is configured separately from application source code.

## Project Purpose

This project was developed to strengthen practical skills in Java backend development, REST API design, authentication, authorization, database integration, and application security.

## Author

**Raheena Shaik**  
GitHub: [shaikraheena1337-code](https://github.com/shaikraheena1337-code)
