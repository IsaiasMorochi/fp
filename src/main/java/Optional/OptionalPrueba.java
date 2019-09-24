package Optional;

import java.util.Optional;

/**
 * Optional nos permite reducir las excepciones de NullPointerException
 * Es un emboltorio del tipo de dato.
 *
 * @since 1.8
 */
public class OptionalPrueba {

    public static void main(String[] arg){
        pruebaOptional("isaias pruebaOptional");
//        pruebaOptional(null); //provoca error en la application

        orElseOptional("Isaias orElseOptional");
//        orElseOptional(null);

        orElseThrow("Isaias orElseThrow");
//        orElseThrow(null);

        isPresent("Isaias isPresent");
//        isPresent(null);
    }

    public static void pruebaOptional(String nombre){
        System.out.println(nombre.length());
    }

    public static void crearOptional(){
        Optional<String> optional = Optional.empty();
        optional.get();
    }

    public static void orElseOptional(String nombre){
        Optional<String> optional = Optional.ofNullable(nombre);
//        Optional<String> optional1 = Optional.of(nombre); //Evitar

        String nombreNullable = optional.orElse("Vacio");
//        String nombreOf = optional1.orElse("Vacio");

        System.out.println(nombreNullable);
//        System.out.println(nombreOf);
    }

    public static void orElseThrow(String nombre){
        Optional<String> optional = Optional.ofNullable(nombre);
        optional.orElseThrow(NullPointerException::new);

        String nombre1 = optional.get();
        System.out.println(nombre1);
    }

    public static void isPresent(String nombre){
        Optional<String> optional = Optional.ofNullable(nombre);
        System.out.println(optional.isPresent()); //null = false
    }

}
