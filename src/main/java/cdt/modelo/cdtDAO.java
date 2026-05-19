package cdt.modelo;

import java.util.ArrayList;

public class CDTDAO {
    public static ArrayList<CDT> historial = new ArrayList<>();
    public static void agregar(CDT c){ historial.add(c); }
    public static int total(){ return historial.size(); }
    public static void limpiar(){ historial.clear(); }
}
