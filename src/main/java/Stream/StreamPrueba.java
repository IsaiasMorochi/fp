package Stream;

import java.util.ArrayList;
import java.util.Arrays;
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

        titulo("Find Firts");
        setUpUser();
        User user = users.stream()
                .filter(e -> e.getNombre().equals("Pepito"))
                .findFirst()
                .orElse(null);
        System.out.println(user.getId() + " " + user.getNombre());

        titulo("FlatMap");
        List<List<String>> nombresVariasListas = new ArrayList<List<String>>(
                Arrays.asList(
                        new ArrayList<String>(Arrays.asList("Albeto", "Maria", "Pedro")),
                        new ArrayList<String>(Arrays.asList("Monica", "Pablo"))
        ));
        List<String> nombreUnicaLista = nombresVariasListas.stream()
                .flatMap(s -> s.stream())
                .collect(Collectors.toList());
        nombreUnicaLista.stream().forEach(e -> System.out.println(e));


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
