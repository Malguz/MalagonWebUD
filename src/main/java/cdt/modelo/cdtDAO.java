package cdt.modelo;

import java.util.ArrayList;

public class cdtDAO {
    public static ArrayList<cdt> historial = new ArrayList<>();
    public static void agregar(cdt c){ historial.add(c); }
    public static int total(){ return historial.size(); }
    public static void limpiar(){ historial.clear(); }
}
