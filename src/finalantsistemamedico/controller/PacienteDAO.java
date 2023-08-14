package finalantsistemamedico.controller;

import finalantsistemamedico.model.Consulta;
import finalantsistemamedico.model.Paciente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO {
    private Connection connection;

    public PacienteDAO(Connection connection) {
        this.connection = connection;
    }

public void create(Paciente paciente) throws SQLException {
    String sql = "INSERT INTO pacientes (Nombre, Apellido, FechaNacimiento, ObraSocial, NumeroSocio, AntecedentesPersonales, AntecedentesFamiliares) VALUES (?, ?, ?, ?, ?, ?, ?)";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setString(1, paciente.getNombre());
        statement.setString(2, paciente.getApellido());

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date fechaNacimientoUtil = null;
        try {
            fechaNacimientoUtil = dateFormat.parse(paciente.getFechaNacimientoString());
        } catch (java.text.ParseException e) {
            e.printStackTrace(); // Manejar la excepción apropiadamente
        }

        java.sql.Date fechaNacimiento = new java.sql.Date(fechaNacimientoUtil.getTime());
        statement.setDate(3, fechaNacimiento);

        statement.setString(4, paciente.getObraSocial());
        statement.setString(5, paciente.getNumeroSocio());
        statement.setString(6, paciente.getAntecedentesPersonales());
        statement.setString(7, paciente.getAntecedentesFamiliares());
        statement.executeUpdate();
    }
}

    public Paciente read(int id) throws SQLException {
        String sql = "SELECT * FROM pacientes WHERE Id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return extractPacienteFromResultSet(resultSet);
                }
            }
        }
        return null;
    }
 public Paciente getPacienteById(int pacienteId) throws SQLException {
        Paciente paciente = null;

        String query = "SELECT * FROM pacientes WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, pacienteId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    paciente = new Paciente();
                    paciente.setId(resultSet.getInt("id"));
                    paciente.setNombre(resultSet.getString("nombre"));
                    paciente.setApellido(resultSet.getString("apellido"));
                    paciente.setFechaNacimiento(resultSet.getDate("fecha_nacimiento"));
                    paciente.setObraSocial(resultSet.getString("obra_social"));
                    // ... Configura el resto de las propiedades del paciente
                }
            }
        }
        
        return paciente;
    }
 public void update(Paciente paciente) throws SQLException {
    String sql = "UPDATE pacientes SET Nombre = ?, Apellido = ?, FechaNacimiento = ?, ObraSocial = ?, NumeroSocio = ?, AntecedentesPersonales = ?, AntecedentesFamiliares = ? WHERE Id = ?";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setString(1, paciente.getNombre());
        statement.setString(2, paciente.getApellido());
        statement.setDate(3, paciente.getFechaNacimiento());
        statement.setString(4, paciente.getObraSocial());
        statement.setString(5, paciente.getNumeroSocio());
        statement.setString(6, paciente.getAntecedentesPersonales());
        statement.setString(7, paciente.getAntecedentesFamiliares());
        statement.setInt(8, paciente.getId());
        statement.executeUpdate();
    }
}



public void delete(int id) throws SQLException {
    String sql = "DELETE FROM pacientes WHERE Id = ?";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setInt(1, id);
        statement.executeUpdate();
    }
}








    public List<Paciente> getAll() throws SQLException {
        List<Paciente> pacientes = new ArrayList<>();
        String sql = "SELECT * FROM pacientes";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Paciente paciente = extractPacienteFromResultSet(resultSet);
                pacientes.add(paciente);
            }
        }
        return pacientes;
    }

    private Paciente extractPacienteFromResultSet(ResultSet resultSet) throws SQLException {
        Paciente paciente = new Paciente();
        paciente.setId(resultSet.getInt("Id"));
        paciente.setNombre(resultSet.getString("Nombre"));
        paciente.setApellido(resultSet.getString("Apellido"));
        paciente.setFechaNacimiento(resultSet.getDate("FechaNacimiento"));
        paciente.setObraSocial(resultSet.getString("ObraSocial"));
        paciente.setNumeroSocio(resultSet.getString("NumeroSocio"));
        paciente.setAntecedentesPersonales(resultSet.getString("AntecedentesPersonales"));
        paciente.setAntecedentesFamiliares(resultSet.getString("AntecedentesFamiliares"));
        return paciente;
    }
    
    public List<Paciente> buscarPorNombreOApellido(String nombre, String apellido) throws SQLException {
    List<Paciente> pacientes = new ArrayList<>();
    String sql = "SELECT * FROM pacientes WHERE Nombre LIKE ? OR Apellido LIKE ?";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setString(1, "%" + nombre + "%");
        statement.setString(2, "%" + apellido + "%");
        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Paciente paciente = new Paciente(
                    resultSet.getInt("Id"),
                    resultSet.getString("Nombre"),
                    resultSet.getString("Apellido"),
                    resultSet.getDate("FechaNacimiento"),
                    resultSet.getString("ObraSocial"),
                    resultSet.getString("NumeroSocio"),
                    resultSet.getString("AntecedentesPersonales"),
                    resultSet.getString("AntecedentesFamiliares")
                );
                pacientes.add(paciente);
            }
        }
    }
    return pacientes;
}
    
      public List<Consulta> getConsultasByPacienteId(int pacienteId) throws SQLException {
        List<Consulta> consultas = new ArrayList<>();

        String query = "SELECT * FROM consultas WHERE paciente_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, pacienteId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Consulta consulta = new Consulta();
                    consulta.setId(resultSet.getInt("id"));
                    consulta.setPacienteId(resultSet.getInt("paciente_id"));
                    consulta.setTitulo(resultSet.getString("titulo"));
                    consulta.setDiagnostico(resultSet.getString("diagnostico"));
                    // ... Configura el resto de las propiedades de la consulta
                    consultas.add(consulta);
                }
            }
        }
        
        return consultas;
    }

    public Paciente getById(int idPacienteSeleccionado) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
