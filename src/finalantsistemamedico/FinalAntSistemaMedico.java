package finalantsistemamedico;

// Clase FinalAntSistemaMedico
import finalantsistemamedico.view.Principal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.SwingUtilities;

public class FinalAntSistemaMedico {
    public static void main(String[] args) throws SQLException {
        String jdbcUrl = "jdbc:sqlite:C:/Users/Public/DatosPacientes/pacientes.db";

        try {
            // Cargar el controlador JDBC
            Class.forName("org.sqlite.JDBC");

            // Establecer la conexión a la base de datos
            Connection connection = DriverManager.getConnection(jdbcUrl);

            if (connection != null) {
                System.out.println("Conexión exitosa a la base de datos");

                SwingUtilities.invokeLater(() -> {
                    Principal principalFrame = new Principal(connection);
                    principalFrame.setVisible(true); // Mostrar la ventana
                });
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Error al cargar el controlador JDBC: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error al establecer la conexión: " + e.getMessage());
        }
    }
}
