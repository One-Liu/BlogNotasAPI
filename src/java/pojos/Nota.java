package pojos;

import java.sql.Timestamp;

public class Nota {
    private Integer idNota;
    private String titulo;
    private String contenido;
    private Timestamp tiempoCreacion;
    private Integer idUsuarioCreacion;
    private Integer eliminado;
    private Integer idLibreta;
    private Integer idPrioridad;

    public Nota() {
    }

    public Nota(Integer idNota, String titulo, String contenido, Timestamp tiempoCreacion, Integer idUsuarioCreacion, Integer eliminado, Integer idLibreta, Integer idPrioridad) {
        this.idNota = idNota;
        this.titulo = titulo;
        this.contenido = contenido;
        this.tiempoCreacion = tiempoCreacion;
        this.idUsuarioCreacion = idUsuarioCreacion;
        this.eliminado = eliminado;
        this.idLibreta = idLibreta;
        this.idPrioridad = idPrioridad;
    }

    public Integer getIdNota() {
        return idNota;
    }

    public void setIdNota(Integer idNota) {
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

    public Integer getIdUsuarioCreacion() {
        return idUsuarioCreacion;
    }

    public void setIdUsuarioCreacion(Integer idUsuarioCreacion) {
        this.idUsuarioCreacion = idUsuarioCreacion;
    }

    public Integer getEliminado() {
        return eliminado;
    }

    public void setEliminado(Integer eliminado) {
        this.eliminado = eliminado;
    }

    public Integer getIdLibreta() {
        return idLibreta;
    }

    public void setIdLibreta(Integer idLibreta) {
        this.idLibreta = idLibreta;
    }

    public Integer getIdPrioridad() {
        return idPrioridad;
    }

    public void setIdPrioridad(Integer idPrioridad) {
        this.idPrioridad = idPrioridad;
    }
}
