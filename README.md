# Technical Test - Backend

Backend API developed with Java 17 and Spring Boot to manage students, subjects, and grades.

## Technologies Used
- Java 17
- Spring Boot 3.3.4
- Spring Data JPA
- Maven
- MySQL 8.0
- Docker & Docker Compose

## Prerequisites
- Docker and Docker Compose installed.

## Environment Variables

The API supports configuration through environment variables (configured in `docker-compose.yml`):

- `DB_URI`: Database connection URI (Default: `jdbc:mysql://db:3306/evaluacion?useSSL=false&allowPublicKeyRetrieval=true`)
- `DB_USER`: Database user (Default: `root`)
- `DB_PASSWORD`: Database password (Default: `root`)
- `DB_DRIVER`: JDBC Driver (Default: `com.mysql.cj.jdbc.Driver`)

## Running Instructions

To start the backend API and the MySQL database using Docker, run the following command in the repository root:

```bash
docker-compose up --build -d
```

Once finished, the API will be available at [http://localhost:8080/alumnos](http://localhost:8080/alumnos).
The MySQL database is exposed on port `3306`.

### Test Data
A database backup (`evaluacion.dump`) is located in the `db/` directory and is automatically executed by Docker during the MySQL initialization.

To stop the containers:
```bash
docker-compose down
```