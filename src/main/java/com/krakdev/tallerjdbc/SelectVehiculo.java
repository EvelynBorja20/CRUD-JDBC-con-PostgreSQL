package com.krakdev.tallerjdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.krakdev.jdbc.Conexion;

public class SelectVehiculo {
    private static final Logger logger = LoggerFactory.getLogger(SelectVehiculo.class);

    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = "SELECT placa, marca, modelo, anio, precio, color, disponible FROM vehiculos";

        try {
            connection = Conexion.conectar();
            
            if (connection != null) {
                pstmt = connection.prepareStatement(sql);
                rs = pstmt.executeQuery();

                logger.info("Listado de vehículos obtenidos con éxito:");
                
                while (rs.next()) {
                    String placa = rs.getString("placa");
                    String marca = rs.getString("marca");
                    String modelo = rs.getString("modelo");
                    int anio = rs.getInt("anio");
                    double precio = rs.getDouble("precio");
                    String color = rs.getString("color");
                    boolean disponible = rs.getBoolean("disponible");

                    System.out.println("-> [Placa: " + placa + " | Marca: " + marca + 
                                       " | Modelo: " + modelo + " | Año: " + anio + 
                                       " | Precio: " + precio + " | Color: " + color + 
                                       " | Disponible: " + disponible + "]");
                }
            }

        } catch (SQLException e) {
            logger.error("Error al consultar los vehículos: {}", e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                logger.error("Error al cerrar los recursos de base de datos: {}", e.getMessage());
            }
        }
    }
}