package pojos;

public class Libreta {
    private int idLibreta;
    private String nombre;
    private String colorHexadecimal;
    private int idUsuario;

    public Libreta() {
    }

    public Libreta(int idLibreta, String nombre, String colorHexadecimal, int idUsuario) {
        this.idLibreta = idLibreta;
        this.nombre = nombre;
        this.colorHexadecimal = colorHexadecimal;
        this.idUsuario = idUsuario;
    }

    public int getIdLibreta() {
        return idLibreta;
    }

    public void setIdLibreta(int idLibreta) {
        this.idLibreta = idLibreta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getColorHexadecimal() {
        return colorHexadecimal;
    }

    public void setColorHexadecimal(String colorHexadecimal) {
        this.colorHexadecimal = colorHexadecimal;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public String toString() {
        return "Libreta{" + "idLibreta=" + idLibreta + ", nombre=" + nombre + ", colorHexadecimal=" + colorHexadecimal + ", idUsuario=" + idUsuario + '}';
    }
}
