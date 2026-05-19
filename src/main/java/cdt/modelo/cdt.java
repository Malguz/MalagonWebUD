package cdt.modelo;
import java.io.Serializable;
import java.time.LocalDate;

public class CDT implements Serializable {
    private static final long serialVersionUID = 1L;
    private double    capital;
    private double    tasaAnual;
    private int       plazoDias;
    private String    titular;
    private String    tipo;
    private LocalDate fechaApertura;
    private double    interesGenerado;
    private double    totalVencimiento;
    private LocalDate fechaVencimiento;
    private double    retencionFuente;
    private double    valorNeto;

    public CDT(){}
    public double getCapital(){return capital;} public void setCapital(double v){capital=v;}
    public double getTasaAnual(){return tasaAnual;} public void setTasaAnual(double v){tasaAnual=v;}
    public int getPlazoDias(){return plazoDias;} public void setPlazoDias(int v){plazoDias=v;}
    public String getTitular(){return titular;} public void setTitular(String v){titular=v;}
    public String getTipo(){return tipo;} public void setTipo(String v){tipo=v;}
    public LocalDate getFechaApertura(){return fechaApertura;} public void setFechaApertura(LocalDate v){fechaApertura=v;}
    public double getInteresGenerado(){return interesGenerado;} public void setInteresGenerado(double v){interesGenerado=v;}
    public double getTotalVencimiento(){return totalVencimiento;} public void setTotalVencimiento(double v){totalVencimiento=v;}
    public LocalDate getFechaVencimiento(){return fechaVencimiento;} public void setFechaVencimiento(LocalDate v){fechaVencimiento=v;}
    public double getRetencionFuente(){return retencionFuente;} public void setRetencionFuente(double v){retencionFuente=v;}
    public double getValorNeto(){return valorNeto;} public void setValorNeto(double v){valorNeto=v;}
}
