
package finalantsistemamedico.controller;

import finalantsistemamedico.model.Consulta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConsultaDAO {
    private Connection connection;

    public ConsultaDAO(Connection connection) {
        this.connection = connection;
    }

    // Constructor y métodos para CRUD

    public ConsultaDAO() {
    }
    
    // Método para insertar una consulta en la base de datos
    public void insert(Consulta consulta) throws SQLException {
        String sql = "INSERT INTO consultas (Titulo, Diagnostico, IdPaciente) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, consulta.getTitulo());
            statement.setString(2, consulta.getDiagnostico());
            statement.setInt(3, consulta.getIdPaciente());
            statement.executeUpdate();
        }
    }
    
    // Método para actualizar una consulta en la base de datos
    public void update(Consulta consulta) throws SQLException {
        String sql = "UPDATE consultas SET Titulo = ?, Diagnostico = ?, IdPaciente = ? WHERE IdConsulta = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, consulta.getTitulo());
            statement.setString(2, consulta.getDiagnostico());
            statement.setInt(3, consulta.getIdPaciente());
            statement.setInt(4, consulta.getIdConsulta());
            statement.executeUpdate();
        }
    }
    
    // Método para eliminar una consulta de la base de datos por su ID
    public void delete(int consultaId) throws SQLException {
        String sql = "DELETE FROM consultas WHERE IdConsulta = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, consultaId);
            statement.executeUpdate();
        }
    }
    
    // Método para obtener todas las consultas de la base de datos
    public List<Consulta> getAllConsultas() throws SQLException {
        List<Consulta> consultas = new ArrayList<>();
        String sql = "SELECT * FROM consultas";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Consulta consulta = new Consulta(
                        resultSet.getInt("IdConsulta"),
                        resultSet.getString("Titulo"),
                        resultSet.getString("Diagnostico"),
                        resultSet.getInt("IdPaciente")
                    );
                    consultas.add(consulta);
                }
            }
        }
        return consultas;
    }
    
    // Otros métodos para obtener consultas por ID, por paciente, etc.
}
