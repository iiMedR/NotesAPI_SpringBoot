# Secure Notes API

A Spring Boot learning project for a REST API where users register, sign in with JWT, and manage their own private notes.

## Current progress

- Spring Boot project with Web MVC, JPA, validation, and PostgreSQL dependencies.
- PostgreSQL configuration using a local `.env` file.
- User and Note entities with a one-to-many relationship.
- Authentication, JWT, repositories, and API endpoints are the next steps.

## Local setup

Requires Java 17 or later and PostgreSQL (or Docker for the included Compose configuration).

1. Copy `.env.example` to `.env` and set your local database credentials.
2. Run `docker compose up -d` to start PostgreSQL on port 5433.
3. On Windows, run `.\mvnw.cmd spring-boot:run`.

The local `.env` file is excluded from Git. If using an existing PostgreSQL database, match its credentials and database name in `.env`.

## Planned endpoints

- `POST /api/auth/register`
- `POST /api/auth/login`
- `POST /api/notes`
- `GET /api/notes`
- `GET /api/notes/{id}`
- `PUT /api/notes/{id}`
- `DELETE /api/notes/{id}`

Every notes endpoint will require authentication and restrict access to the note owner.
