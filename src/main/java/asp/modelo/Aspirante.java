package asp.modelo;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Aspirante extends Persona implements Serializable {

    private static final long serialVersionUID = 1L;

    private LocalDate fecha_reg;
    private ProgAcad pro_acad;
    private String tipo_doc;         

    public Aspirante() {
        super();
        pro_acad = new ProgAcad();
    }

    public Aspirante(long id_p, String nombres, String apellidos, String telefono, String correo,
                     LocalDate fecha_reg, ProgAcad pro_acad, String tipo_doc) {
        super(id_p, nombres, apellidos, telefono, correo);
        this.fecha_reg = fecha_reg;
        this.pro_acad = pro_acad;
        this.tipo_doc = tipo_doc;
       
    }

    public LocalDate getFecha_reg() {
        return fecha_reg;
    }

    public void setFecha_reg(LocalDate fecha_reg) {
        this.fecha_reg = fecha_reg;
    }

    public ProgAcad getPro_acad() {
        return pro_acad;
    }

    public void setPro_acad(ProgAcad pro_acad) {
        this.pro_acad = pro_acad;
    }

    public String getTipo_doc() {
        return tipo_doc;
    }

    public void setTipo_doc(String tipo_doc) {
        this.tipo_doc = tipo_doc;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Objects.hash(fecha_reg, pro_acad, tipo_doc);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!super.equals(obj)) return false;
        if (getClass() != obj.getClass()) return false;
        Aspirante other = (Aspirante) obj;
        return Objects.equals(fecha_reg, other.fecha_reg)
                && Objects.equals(pro_acad, other.pro_acad);
    }

    @Override
    public String toString() {
        return "Aspirante [id=" + getId_p() + ", nombre=" + getNombres()
                + ", apellidos=" + getApellidos()
                + ", programa=" + (pro_acad != null ? pro_acad.getNombre_prog() : "N/A")
                + ", fecha_reg=" + fecha_reg + "]";
    }
}
