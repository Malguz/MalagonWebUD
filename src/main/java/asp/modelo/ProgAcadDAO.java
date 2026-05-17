package asp.modelo;



import java.util.ArrayList;


public class ProgAcadDAO {

    public static ArrayList<ProgAcad> lista_P = new ArrayList<ProgAcad>();

    public static void cargaDatos() {
        if (lista_P.isEmpty()) {
            System.out.println("[ProgAcadDAO] Cargando programas académicos...");
            lista_P.add(new ProgAcad(10, "Ingeniería de Sistemas",         "Facultad de Ingeniería"));
            lista_P.add(new ProgAcad(20, "Ingeniería Industrial",           "Facultad de Ingeniería"));
            lista_P.add(new ProgAcad(30, "Ingeniería de Telemática",        "Facultad de Ingeniería"));
            lista_P.add(new ProgAcad(40, "Ingeniería Electrónica",          "Facultad de Ingeniería"));
            lista_P.add(new ProgAcad(50, "Ingeniería Civil",                "Facultad de Ingeniería"));
            lista_P.add(new ProgAcad(60, "Licenciatura en Matemáticas",     "Facultad de Ciencias y Educación"));
            lista_P.add(new ProgAcad(70, "Licenciatura en Informática",     "Facultad de Ciencias y Educación"));
            lista_P.add(new ProgAcad(80, "Artes Escénicas",                 "Facultad de Artes - ASAB"));
            lista_P.add(new ProgAcad(90, "Diseño Industrial",               "Facultad de Artes - ASAB"));
            lista_P.add(new ProgAcad(100, "Administración Ambiental",       "Fac. Medio Ambiente y Recursos Naturales"));
            System.out.println("[ProgAcadDAO] " + lista_P.size() + " programas cargados.");
        }
    }

    public static ProgAcad buscarPorCod(int cod) {
        for (ProgAcad p : lista_P) {
            if (p.getCod() == cod) {
                return p;
            }
        }
        return null;
    }
}
