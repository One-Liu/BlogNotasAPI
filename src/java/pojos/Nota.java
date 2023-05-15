package pojos;

import java.sql.Timestamp;

public class Nota {
    private int idNota;
    private String titulo;
    private String contenido;
    private Timestamp tiempoCreacion;
    private int idUsuarioCreacion;
    private int eliminado;
    private int idLibreta;
    private int idPrioridad;

    public Nota() {
    }

    public Nota(int idNota, String titulo, String contenido, Timestamp tiempoCreacion, int idUsuarioCreacion, int eliminado, int idLibreta, int idPrioridad) {
        this.idNota = idNota;
        this.titulo = titulo;
        this.contenido = contenido;
        this.tiempoCreacion = tiempoCreacion;
        this.idUsuarioCreacion = idUsuarioCreacion;
        this.eliminado = eliminado;
        this.idLibreta = idLibreta;
        this.idPrioridad = idPrioridad;
    }

    public int getIdNota() {
        return idNota;
    }

    public void setIdNota(int idNota) {
        this.idNota = idNota;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Timestamp getTiempoCreacion() {
        return tiempoCreacion;
    }

    public void setTiempoCreacion(Timestamp tiempoCreacion) {
        this.tiempoCreacion = tiempoCreacion;
    }

    public int getIdUsuarioCreacion() {
        return idUsuarioCreacion;
    }

    public void setIdUsuarioCreacion(int idUsuarioCreacion) {
        this.idUsuarioCreacion = idUsuarioCreacion;
    }

    public int getEliminado() {
        return eliminado;
    }

    public void setEliminado(int eliminado) {
        this.eliminado = eliminado;
    }

    public int getIdLibreta() {
        return idLibreta;
    }

    public void setIdLibreta(int idLibreta) {
        this.idLibreta = idLibreta;
    }

    public int getIdPrioridad() {
        return idPrioridad;
    }

    public void setIdPrioridad(int idPrioridad) {
        this.idPrioridad = idPrioridad;
    }
}
