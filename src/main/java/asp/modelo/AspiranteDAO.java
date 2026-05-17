package asp.modelo;
import java.util.ArrayList;

public class AspiranteDAO {

    public static ArrayList<Aspirante> lista_A = new ArrayList<Aspirante>();

 
    public static void agregar(Aspirante a) {
        lista_A.add(a);
        System.out.println("[AspiranteDAO] Aspirante registrado: " + a.toString());
    }

   
    public static boolean existeId(long id) {
        for (Aspirante a : lista_A) {
            if (a.getId_p() == id) {
                return true;
            }
        }
        return false;
    }

   
    public static boolean existeCorreo(String correo) {
        for (Aspirante a : lista_A) {
            if (a.getCorreo() != null && a.getCorreo().equalsIgnoreCase(correo)) {
                return true;
            }
        }
        return false;
    }

   
    public static int total() {
        return lista_A.size();
    }
}
