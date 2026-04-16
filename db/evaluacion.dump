CREATE DATABASE IF NOT EXISTS evaluacion;
USE evaluacion;

CREATE TABLE IF NOT EXISTS alumnos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    apellido VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    fecha_nacimiento DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS materias (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    codigo VARCHAR(255) NOT NULL UNIQUE,
    creditos INT NOT NULL
);

CREATE TABLE IF NOT EXISTS notas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    valor DOUBLE NOT NULL,
    fecha_registro DATE NOT NULL,
    alumno_id BIGINT NOT NULL,
    materia_id BIGINT NOT NULL,
    FOREIGN KEY (alumno_id) REFERENCES alumnos(id),
    FOREIGN KEY (materia_id) REFERENCES materias(id)
);

INSERT INTO alumnos (nombre, apellido, email, fecha_nacimiento) VALUES
('Juan', 'Perez', 'juan@example.com', '2000-01-01'),
('Maria', 'Gomez', 'maria@example.com', '1999-05-15');

INSERT INTO materias (nombre, codigo, creditos) VALUES
('Matematicas', 'MAT101', 4),
('Fisica', 'FIS101', 3);

INSERT INTO notas (valor, fecha_registro, alumno_id, materia_id) VALUES
(4.5, '2023-01-01', 1, 1),
(3.8, '2023-01-02', 2, 1);
