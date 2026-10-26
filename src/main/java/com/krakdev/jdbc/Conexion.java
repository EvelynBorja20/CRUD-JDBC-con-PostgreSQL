package com.krakdev.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Conexion {
    // Definimos el logger usando SLF4J (viene por defecto con Spring Boot / Logback)
    private static final Logger logger = LoggerFactory.getLogger(Conexion.class);

    private static final String URL = "jdbc:postgresql://localhost:5432/tallerjdbc";
    private static final String USER = "tu_usuario";     // HOMECAR
    private static final String PASS = "tu_contrasena"; // 1234567
    public static Connection conectar() {
        Connection connection = null;
        try {
            // Registrar y obtener la conexión
            connection = DriverManager.getConnection(URL, USER, PASS);
            logger.info("Conexión realizada con éxito a la base de datos.");
        } catch (SQLException e) {
            logger.error("Error de conexión a la base de datos: {}", e.getMessage());
        }
        return connection;
    }
}

