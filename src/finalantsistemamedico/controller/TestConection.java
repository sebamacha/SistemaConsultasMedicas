
package finalantsistemamedico.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConection {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:sqlite:C:/Users/Public/DatosPacientes/pacientes.db";
//        String user = ""; // Si no es necesario
//        String password = ""; // Si no es necesario
//        
        try {
            // Cargar el controlador JDBC
            Class.forName("org.sqlite.JDBC");
            
            // Establecer la conexión a la base de datos
            Connection connection = DriverManager.getConnection(jdbcUrl);
            
            if (connection != null) {
                System.out.println("Conexión exitosa a la base de datos");
                
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Error al cargar el controlador JDBC: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error al establecer la conexión: " + e.getMessage());
        }
    }
}
