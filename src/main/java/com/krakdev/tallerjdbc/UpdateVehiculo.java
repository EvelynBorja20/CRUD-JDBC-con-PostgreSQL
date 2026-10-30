package com.krakdev.tallerjdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.krakdev.jdbc.Conexion; // Ajusta si Conexion está en otro paquete, o ponlos en el mismo

public class UpdateVehiculo {
    private static final Logger logger = LoggerFactory.getLogger(UpdateVehiculo.class);

    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement pstmt = null;

        String sql = "UPDATE vehiculos SET precio = ?, color = ? WHERE placa = ?";

        try {
            connection = Conexion.conectar();
            
            if (connection != null) {
                pstmt = connection.prepareStatement(sql);
                
                // Nuevos valores que vamos a actualizar
                pstmt.setDouble(1, 23500.00); // Nuevo precio
                pstmt.setString(2, "Azul");     // Nuevo color
                pstmt.setString(3, "ABC-1234"); // Placa del vehículo a buscar

                int filasAfectadas = pstmt.executeUpdate();
                
                if (filasAfectadas > 0) {
                    logger.info("Vehículo actualizado con éxito en la base de datos.");
                } else {
                    logger.warn("No se encontró ningún vehículo con la placa especificada.");
                }
            }

        } catch (SQLException e) {
            logger.error("Error al actualizar el vehículo: {}", e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                logger.error("Error al cerrar los recursos: {}", e.getMessage());
            }
        }
    }
}
