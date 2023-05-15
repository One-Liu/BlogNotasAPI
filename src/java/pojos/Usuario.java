package pojos;

import java.sql.Timestamp;

public class Usuario {
    private int idUsuario;
    private String nombres;
    private String apellidos;
    private Timestamp tiempoRegistro;
    private int activo;
    private String celular;
    private String contrasena;
    private String ultimotokenAcceso;
    private Timestamp tiempoUltimoAcceso;
    private String otp;
    private Timestamp tiempoActivacion;

    public Usuario() {
    }

    public Usuario(int idUsuario, String nombres, String apellidos, Timestamp tiempoRegistro, int activo, String celular, String contrasena, String ultimotokenAcceso, Timestamp tiempoUltimoAcceso, String otp, Timestamp tiempoActivacion) {
        this.idUsuario = idUsuario;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.tiempoRegistro = tiempoRegistro;
        this.activo = activo;
        this.celular = celular;
        this.contrasena = contrasena;
        this.ultimotokenAcceso = ultimotokenAcceso;
        this.tiempoUltimoAcceso = tiempoUltimoAcceso;
        this.otp = otp;
        this.tiempoActivacion = tiempoActivacion;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
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

    public Timestamp getTiempoRegistro() {
        return tiempoRegistro;
    }

    public void setTiempoRegistro(Timestamp tiempoRegistro) {
        this.tiempoRegistro = tiempoRegistro;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getUltimotokenAcceso() {
        return ultimotokenAcceso;
    }

    public void setUltimotokenAcceso(String ultimotokenAcceso) {
        this.ultimotokenAcceso = ultimotokenAcceso;
    }

    public Timestamp getTiempoUltimoAcceso() {
        return tiempoUltimoAcceso;
    }

    public void setTiempoUltimoAcceso(Timestamp tiempoUltimoAcceso) {
        this.tiempoUltimoAcceso = tiempoUltimoAcceso;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public Timestamp getTiempoActivacion() {
        return tiempoActivacion;
    }

    public void setTiempoActivacion(Timestamp tiempoActivacion) {
        this.tiempoActivacion = tiempoActivacion;
    }

    @Override
    public String toString() {
        return "Usuario{" + "idUsuario=" + idUsuario + ", nombres=" + nombres + ", apellidos=" + apellidos + ", tiempoRegistro=" + tiempoRegistro + ", activo=" + activo + ", celular=" + celular + ", contrasena=" + contrasena + ", ultimotokenAcceso=" + ultimotokenAcceso + ", tiempoUltimoAcceso=" + tiempoUltimoAcceso + ", otp=" + otp + ", tiempoActivacion=" + tiempoActivacion + '}';
    }
}
