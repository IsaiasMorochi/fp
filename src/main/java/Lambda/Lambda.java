package Lambda;


public class Lambda implements PorDefecto{

    public static void main(String[] args){
        System.out.println(System.getProperty("java.runtime.version"));

        /**
         * Formato funcion lambda
         * argumentos   operador  cuerpo
         * (parametros)    ->     expresion
         */

       /*
        () -> "Mi nombre es: ";
        (n) -> n *n ;
        (n) -> n == 2; */

       // 1
       MiNombre miNombreAnonima = new MiNombre() {
           public String miNombre() {
               return "Anonima";
           }
       };
        System.out.println(miNombreAnonima.miNombre());

        MiNombre miNombreLambda = () -> "Lambda";
        System.out.println(miNombreLambda.miNombre());


        // 2
        Sumar suma = new Sumar() {
            @Override
            public Integer sumar(int a, int b) {
                return a + b;
            }
        };
        System.out.println(suma.sumar(2,3));

        Sumar suma1 = (a,b) -> a + b;
        System.out.println(suma1.sumar(2,3));

        // 3
        Sumar suma2 = (a,b) -> {
            a = b * b;
            a = a + b;
            System.out.println("Mensaje dentro del lambda");
            return  a;
        };
        System.out.println(suma2.sumar(2,3));

        Lambda l = new Lambda();
        System.out.println(l.nombrePorDefecto("Isaias"));

    }

    @Override
    public void mostrarNombre(String nombre) {

    }
}
