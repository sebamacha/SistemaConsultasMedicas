package finalantsistemamedico.model;

import java.sql.Date;  // Importar la clase java.sql.Date
import java.text.SimpleDateFormat;

public class Paciente {
    private int id;
    private String nombre;
    private String apellido;
    private Date fechaNacimiento;  // Cambiar el tipo de dato
    private String obraSocial;
    private String numeroSocio;
    private String antecedentesPersonales;
    private String antecedentesFamiliares;

    public Paciente() {
    }

    public Paciente(int id, String nombre, String apellido, Date fechaNacimiento, String obraSocial, String numeroSocio, String antecedentesPersonales, String antecedentesFamiliares) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.obraSocial = obraSocial;
        this.numeroSocio = numeroSocio;
        this.antecedentesPersonales = antecedentesPersonales;
        this.antecedentesFamiliares = antecedentesFamiliares;
    }
    public String getFechaNacimientoString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(fechaNacimiento);
    }

    // Getters y setters

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    
    

    // Otros getters y setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getObraSocial() {
        return obraSocial;
    }

    public void setObraSocial(String obraSocial) {
        this.obraSocial = obraSocial;
    }

    public String getNumeroSocio() {
        return numeroSocio;
    }

    public void setNumeroSocio(String numeroSocio) {
        this.numeroSocio = numeroSocio;
    }

    public String getAntecedentesPersonales() {
        return antecedentesPersonales;
    }

    public void setAntecedentesPersonales(String antecedentesPersonales) {
        this.antecedentesPersonales = antecedentesPersonales;
    }

    public String getAntecedentesFamiliares() {
        return antecedentesFamiliares;
    }

    public void setAntecedentesFamiliares(String antecedentesFamiliares) {
        this.antecedentesFamiliares = antecedentesFamiliares;
    }
}
