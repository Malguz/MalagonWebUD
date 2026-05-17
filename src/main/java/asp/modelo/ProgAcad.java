package asp.modelo;



import java.io.Serializable;
import java.util.Objects;

public class ProgAcad implements Serializable {

    private static final long serialVersionUID = 1L;

    private int cod;
    private String nombre_prog;
    private String facultad;

    public ProgAcad() {
    }

    public ProgAcad(int cod, String nombre_prog, String facultad) {
        this.cod = cod;
        this.nombre_prog = nombre_prog;
        this.facultad = facultad;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getNombre_prog() {
        return nombre_prog;
    }

    public void setNombre_prog(String nombre_prog) {
        this.nombre_prog = nombre_prog;
    }

    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cod, nombre_prog);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        ProgAcad other = (ProgAcad) obj;
        return cod == other.cod && Objects.equals(nombre_prog, other.nombre_prog);
    }

    @Override
    public String toString() {
        return "ProgAcad [cod=" + cod + ", nombre_prog=" + nombre_prog + ", facultad=" + facultad + "]";
    }
}
