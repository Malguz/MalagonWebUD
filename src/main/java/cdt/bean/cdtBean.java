package cdt.bean;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

import cdt.modelo.cdt;
import cdt.modelo.cdtDAO;
import asp.modelo.Aspirante;

import jakarta.faces.application.FacesMessage;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;

@Named(value = "cdt")
@SessionScoped
public class cdtBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private cdt dto = new cdt();
    private boolean calculado = false;

    public cdtBean() {
        dto.setFechaApertura(LocalDate.now());
        dto.setTipo("simple");
    }

    public cdt getDto() { return dto; }
    public void setDto(cdt dto) { this.dto = dto; }
    public boolean isCalculado() { return calculado; }
    public ArrayList<cdt> getHistorial() { return cdtDAO.historial; }
    public int getTotalSimulaciones() { return cdtDAO.total(); }

    public void calcular() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        calculado = false;

        if (dto.getCapital() < 500_000) {
            ctx.addMessage("formCDT:capital", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "Capital minimo $500.000 COP.", null));
            return;
        }
        if (dto.getTasaAnual() <= 0 || dto.getTasaAnual() > 30) {
            ctx.addMessage("formCDT:tasaAnual", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "La tasa debe estar entre 0.01% y 30%.", null));
            return;
        }
        if (dto.getPlazoDias() < 30) {
            ctx.addMessage("formCDT:plazoDias", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "Plazo minimo 30 dias.", null));
            return;
        }

        double capital = dto.getCapital();
        double tasaD   = dto.getTasaAnual() / 100.0 / 365.0;
        int    dias    = dto.getPlazoDias();
        double interes, total;

        if ("compuesto".equalsIgnoreCase(dto.getTipo())) {
            total   = capital * Math.pow(1 + tasaD, dias);
            interes = total - capital;
        } else {
            interes = capital * tasaD * dias;
            total   = capital + interes;
        }

        // Retencion en la fuente: 4% si intereses > 10% UVT 2026 ($4.979,9)
        double retencion = interes > 4979.9 ? interes * 0.04 : 0.0;
        double neto = total - retencion;

        dto.setInteresGenerado(interes);
        dto.setTotalVencimiento(total);
        dto.setRetencionFuente(retencion);
        dto.setValorNeto(neto);
        dto.setFechaVencimiento(dto.getFechaApertura().plusDays(dias));

        cdtDAO.agregar(copiar(dto));
        calculado = true;

        ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
            "Calculo exitoso para: " + dto.getTitular(), null));
    }

    public void nueva() {
        dto = new cdt();
        dto.setFechaApertura(LocalDate.now());
        dto.setTipo("simple");
        calculado = false;
    }

    public void limpiarHistorial() { cdtDAO.limpiar(); }

    private cdt copiar(cdt o) {
        cdt c = new cdt();
        c.setCapital(o.getCapital()); c.setTasaAnual(o.getTasaAnual());
        c.setPlazoDias(o.getPlazoDias()); c.setTitular(o.getTitular());
        c.setTipo(o.getTipo()); c.setFechaApertura(o.getFechaApertura());
        c.setInteresGenerado(o.getInteresGenerado()); c.setTotalVencimiento(o.getTotalVencimiento());
        c.setRetencionFuente(o.getRetencionFuente()); c.setValorNeto(o.getValorNeto());
        c.setFechaVencimiento(o.getFechaVencimiento());
        return c;
    }
}