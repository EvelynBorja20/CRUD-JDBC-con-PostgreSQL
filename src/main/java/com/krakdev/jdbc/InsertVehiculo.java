package com.krakdev.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InsertVehiculo {
    private static final Logger logger = LoggerFactory.getLogger(InsertVehiculo.class);

    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement pstmt = null;

        String sql = "INSERT INTO vehiculos (placa, marca, modelo, anio, precio, color, disponible) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            // Obtenemos la conexión de nuestra clase utilitaria
            connection = Conexion.conectar();
            
            if (connection != null) {
                // Preparamos la sentencia SQL
                pstmt = connection.prepareStatement(sql);
                
                // Asignamos los valores a cada parámetro ?
                pstmt.setString(1, "ABC-1234");
                pstmt.setString(2, "Toyota");
                pstmt.setString(3, "Corolla");
                pstmt.setInt(4, 2022);
                pstmt.setDouble(5, 22500.00);
                pstmt.setString(6, "Rojo");
                pstmt.setBoolean(7, true);

                // Ejecutamos la inserción
                pstmt.executeUpdate();
                logger.info("Vehículo insertado con éxito en la base de datos.");
            }

        } catch (SQLException e) {
            logger.error("Error al insertar el vehículo: {}", e.getMessage());
        } finally {
            // Bloque finally para cerrar conexiones y evitar fugas de memoria
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                logger.error("Error al cerrar la conexión: {}", e.getMessage());
            }
        }
    }
}