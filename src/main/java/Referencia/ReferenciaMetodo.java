package Referencia;

import java.util.ArrayList;
import java.util.List;

public class ReferenciaMetodo {

    /**
     * Tipos de metodos de Referencia
     * Tipo                 Syntax                           Method Reference      Lambda expresion
     * ----------------------------------------------------------------------------------------------------
     * Referencia a         Class::staticMethod              Math::abs             n -> Math.abs(n)
     * static method
     * ----------------------------------------------------------------------------------------------------
     * Referencia a un      instancia::metodoInstancia       s:toString            () -> "string".toString
     * metodo de instancia
     * de un objeto
     * particular
     * ----------------------------------------------------------------------------------------------------
     * Referencia a un      Class::metodoInstancia           String::toString      s -> s.toString()
     * metodo de instancia
     * de un objeto
     * arbitrario de
     * un tipo particular
     * ----------------------------------------------------------------------------------------------------
     * Referencia a un     Class::new                        String::new            () -> new String
     * Constructor
     *
     * ----------------------------------------------------------------------------------------------------
     *
     */

    public static void main(String[] arg){

        /* Referencia a         Class::staticMethod              Math::abs             n -> Math.abs(n)
         * static method
         */
        Trabajo trabajo = new Trabajo() {
            @Override
            public void accion() {
                User.referenciaMetodoEstatico();
            }
        };

        //lambda
        Trabajo trabajoL = () -> User.referenciaMetodoEstatico();
        Trabajo trabajoMR = User::referenciaMetodoEstatico;
        trabajoMR.accion();

        /* Referencia a un      instancia::metodoInstancia       s:toString            () -> "string".toString
         * metodo de instancia
         * de un objeto
         * particular
         */

        User user = new User("Isaias");
        Trabajo trabajoL2 = () -> user.referenciaMetodoParticular();
        Trabajo trabajoMR2 = user::referenciaMetodoParticular;
        trabajoMR2.accion();


        /* ----------------------------------------------------------------------------------------------------
         * Referencia a un      Class::metodoInstancia           String::toString      s -> s.toString()
         * metodo de instancia
         * de un objeto
         * arbitrario de
         * un tipo particular
         */
        TrabajoString trabajoString = (palabra) -> palabra.toUpperCase();
        TrabajoString trabajoStringMR = String::toUpperCase;
        System.out.println(trabajoStringMR.accion("isaias"));

        List<User> users = new ArrayList<>();
        users.add(new User("Isaias"));
        users.add(new User("Pedro"));
        users.add(new User("Juan"));
        users.add(new User("Carlos"));

        users.forEach(User::mostrarNombre);


        /* Referencia a un     Class::new                        String::new            () -> new String
         * Constructor
         */
        IUser user1 = new IUser() {
            @Override
            public User crear(String nombre) {
                return new User(nombre);
            }
        };

        //lambda
        IUser user2 = (nombre -> new User(nombre));
        //referencia a constructor
        IUser user3 = User::new;



    }
}
