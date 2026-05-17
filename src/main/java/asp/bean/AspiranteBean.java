package asp.bean;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Pattern;

import asp.modelo.Aspirante;
import asp.modelo.AspiranteDAO;
import asp.modelo.ProgAcad;
import asp.modelo.ProgAcadDAO;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.ValidatorException;
import jakarta.inject.Named;

@Named("asp")
@SessionScoped
public class AspiranteBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Aspirante dto = new Aspirante();

    private ArrayList<Aspirante> listaAs = AspiranteDAO.lista_A;

    private ArrayList<ProgAcad> listaPa = ProgAcadDAO.lista_P;

    private boolean registroExitoso = false;

    private String mensajeEstado = "";

    public AspiranteBean() {
        ProgAcadDAO.cargaDatos();
    }

    public Aspirante getDto() { return dto; }
    public void setDto(Aspirante dto) { this.dto = dto; }

    public ArrayList<Aspirante> getListaAs() { return listaAs; }
    public void setListaAs(ArrayList<Aspirante> listaAs) { this.listaAs = listaAs; }

    public ArrayList<ProgAcad> getListaPa() { return listaPa; }
    public void setListaPa(ArrayList<ProgAcad> listaPa) { this.listaPa = listaPa; }

    public boolean isRegistroExitoso() { return registroExitoso; }
    public String getMensajeEstado() { return mensajeEstado; }

    public void registrar() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        registroExitoso = false;
        mensajeEstado = "";

        if (AspiranteDAO.existeId(dto.getId_p())) {
            ctx.addMessage("formAsp:id_p",
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Ya existe un aspirante con el número " + dto.getId_p(), null));
            return;
        }

        if (AspiranteDAO.existeCorreo(dto.getCorreo())) {
            ctx.addMessage("formAsp:correo",
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "El correo " + dto.getCorreo() + " ya está registrado.", null));
            return;
        }

        ProgAcad progSeleccionado = ProgAcadDAO.buscarPorCod(dto.getPro_acad().getCod());
        if (progSeleccionado == null) {
            ctx.addMessage("formAsp:pro_aca",
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Debe seleccionar un programa académico válido.", null));
            return;
        }

        dto.setFecha_reg(LocalDate.now());
        dto.setPro_acad(progSeleccionado);

        AspiranteDAO.agregar(dto);

        registroExitoso = true;
        mensajeEstado = "Aspirante " + dto.getNombres() + " " + dto.getApellidos()
                + " registrado exitosamente.";
        ctx.addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_INFO, mensajeEstado, null));

        dto = new Aspirante();
    }

    public void limpiar() {
        dto = new Aspirante();
        registroExitoso = false;
        mensajeEstado = "";
    }
   
    public int getTotalAspiranteS() {
        return AspiranteDAO.total();
    }

    public void validarCorreo(FacesContext ctx, UIComponent comp, Object value)
            throws ValidatorException {
        if (value == null || value.toString().trim().isEmpty()) {
            return; 
        }
        String correo = value.toString().trim();
        String regex = "^[\\w.+\\-]+@[a-zA-Z0-9\\-]+\\.[a-zA-Z]{2,}$";
        if (!Pattern.matches(regex, correo)) {
            throw new ValidatorException(new FacesMessage(
                FacesMessage.SEVERITY_ERROR,
                "Formato de correo inválido. Ejemplo: usuario@correo.com", null));
        }
    }
}
