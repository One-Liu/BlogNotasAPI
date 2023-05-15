package pojos;

public class SesionToken {

    private Integer idUsuario;
    private String nombres;
    private String apellidos;
    private String celular;
    private String tokenacceso;

    public SesionToken() {
    }

    public SesionToken(Integer idUsuario, String nombres, String apellidos, String celular, String tokenacceso) {
        this.idUsuario = idUsuario;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.celular = celular;
        this.tokenacceso = tokenacceso;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getTokenacceso() {
        return tokenacceso;
    }

    public void setTokenacceso(String tokenacceso) {
        this.tokenacceso = tokenacceso;
    }
}
