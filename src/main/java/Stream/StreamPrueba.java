package Stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Stream representa una secuencia de elementos
 * Stream son emboltorios alrededor de una fuente de datos y hacer su procesameinto masivo
 *
 * @since 1.8
 */
public class StreamPrueba {

    private static List<User> users;

    public static void main(String[] arg){
        setUpUser();

        Stream stream = Stream.of(users);
        users.stream();

        titulo("forEach");
        users.stream().forEach(user -> user.setNombre(user.getNombre() + " Apellido"));
        imprimirLista();

        titulo("Map y Collectors.toList");
        List<String> list = users.stream().map(user -> user.getNombre()).collect(Collectors.toList());
        list.stream().forEach(s -> System.out.println(s));

        titulo("Filters");
        setUpUser();
        List<User> usersFilters = users.stream()
                .filter(user -> user.getNombre() != "Pepito")
                .filter(user -> user.getId() < 3)
                .collect(Collectors.toList());
        usersFilters.stream().forEach(user -> System.out.println(user.getId() + " " + user.getNombre()));
    }

    public static void setUpUser(){
        users = new ArrayList<>();
        users.add(new User(1,"Carlos"));
        users.add(new User(2,"Pepito"));
        users.add(new User(3,"Jose"));
        users.add(new User(4,"Maria"));
        users.add(new User(5,"Pepito"));
    }

    private static void imprimirLista(){
        users.stream().forEach(user -> System.out.println(user.getId() + " " + user.getNombre()));
    }

    private static void titulo(String titulo){
        System.out.println("------------------------"+ titulo +"-------------------------------");
    }
}
