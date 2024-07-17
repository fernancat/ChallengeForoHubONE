CREATE TABLE Topic (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           titulo VARCHAR(255) NOT NULL,
                           contenido TEXT NOT NULL,
                           fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           autor VARCHAR(255) NOT NULL,
                           curso VARCHAR(255) NOT NULL
);