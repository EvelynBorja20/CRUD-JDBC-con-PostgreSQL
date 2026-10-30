package com.krakdev.tallerjdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.krakdev.jdbc.Conexion;

public class DeleteVehiculo {
    private static final Logger logger = LoggerFactory.getLogger(DeleteVehiculo.class);

    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement pstmt = null;

        String sql = "DELETE FROM vehiculos WHERE placa = ?";

        try {
            connection = Conexion.conectar();
            
            if (connection != null) {
                pstmt = connection.prepareStatement(sql);
                
                // Especificamos la placa del vehículo que queremos eliminar
                pstmt.setString(1, "ABC-1234");

                int filasAfectadas = pstmt.executeUpdate();
                
                if (filasAfectadas > 0) {
                    logger.info("Vehículo eliminado con éxito de la base de datos.");
                } else {
                    logger.warn("No se encontró ningún vehículo con la placa especificada para eliminar.");
                }
            }

        } catch (SQLException e) {
            logger.error("Error al eliminar el vehículo: {}", e.getMessage());
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