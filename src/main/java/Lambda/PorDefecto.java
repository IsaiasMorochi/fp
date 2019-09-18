package Lambda;

public interface PorDefecto {

    void mostrarNombre(String nombre);

    // java 8
    default String nombrePorDefecto(String nombre){
        return nombre + " Default";
    }
}
