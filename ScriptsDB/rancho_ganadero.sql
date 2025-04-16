CREATE DATABASE IF NOT EXISTS rancho_ganadero;
USE rancho_ganadero;
CREATE TABLE Animal (
 id_animal INT AUTO_INCREMENT PRIMARY KEY,
 identificacion VARCHAR(50) UNIQUE NOT NULL,
 raza VARCHAR(50) NOT NULL,
 sexo VARCHAR(50) NOT NULL,
 fecha_nacimiento DATE NOT NULL,
 peso_nacimiento DECIMAL(5,2),
 nombre VARCHAR(100),
 color_pelaje VARCHAR(50),
 notas TEXT,
 es_comprado BOOLEAN NOT NULL,
 fecha_compra DATE,
 hato_origen VARCHAR(100),
 fotografia VARCHAR(200)
);
CREATE TABLE Evento (
 id_evento INT AUTO_INCREMENT PRIMARY KEY,
 id_animal INT,
 tipo_evento VARCHAR(50) NOT NULL,
 fecha_evento DATE NOT NULL,
 estado VARCHAR(50),
 detalles TEXT,
 FOREIGN KEY (id_animal) REFERENCES Animal(id_animal)
 );