
package finalantsistemamedico.model;



public class Consulta {
    private int idConsulta;
    private String titulo;
    private String diagnostico;
    private int idPaciente;

    public Consulta() {
    }

    public Consulta(int idConsulta, String titulo, String diagnostico, int idPaciente) {
        this.idConsulta = idConsulta;
        this.titulo = titulo;
        this.diagnostico = diagnostico;
        this.idPaciente = idPaciente;
    }

    public int getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(int idConsulta) {
        this.idConsulta = idConsulta;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    
   
    
}
