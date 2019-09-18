package Referencia;

public class User {

    private String nombre;

    public User(String nombre){
        this.nombre = nombre;
    }

    public static void referenciaMetodoEstatico(){
        System.out.println("Probando referencia a Metodo Estatico");
    }

    public void referenciaMetodoParticular(){
        System.out.println("Probando Referencia a Metodo de Objeto Particular");
    }

    public void mostrarNombre(){
        System.out.println(nombre);
    }
}
