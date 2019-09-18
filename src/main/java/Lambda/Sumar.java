package Lambda;

/**
 * Interfaz funcional
 * Son aquellas que solo tienen un metodo abstracto
 * usar anotacion @FunctionalInterface asi el IDE
 * nos advierte si se quiere agregar mas metodos
 */

@FunctionalInterface
public interface Sumar {

    Integer sumar(int a, int b);

}
