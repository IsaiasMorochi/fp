package Stream;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
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

        titulo("Peek");
        setUpUser();
        List<User> userList = users.stream()
                .peek(user1 -> user1.setNombre(user1.getNombre() + " Apellido"))
                .collect(Collectors.toList());
        userList.stream().forEach(user1 -> System.out.println(user1.getNombre()));

        titulo("Count");
        setUpUser();
        long numeroFiltrado = users.stream()
                .filter(user1 -> user1.getId() < 3)
                .count();
        System.out.println(numeroFiltrado);

        titulo("Skip y Limit");
        String[] abc = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
        List<String> abcFilter = Arrays.stream(abc)
                .skip(2)  //salta los elementos
                .limit(4) //los limita solo a 4 elementos
                .collect(Collectors.toList());
        abcFilter.stream().forEach(s -> System.out.println(s));

        titulo("Sorted");
        setUpUser();
        users = users.stream()
                .sorted(Comparator.comparing(User::getNombre))
                .collect(Collectors.toList());
        imprimirLista();

        titulo("Min y Max");
        setUpUser();
        User userMin = users.stream()
                .min(Comparator.comparing(User::getId))
                .orElse(null);
        System.out.println(userMin.getId());
        User userMax = users.stream()
                .max(Comparator.comparing(User::getId))
                .orElse(null);
        System.out.println(userMax.getId());

        titulo("Distinct");
        String[] abc1 = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j","a","b"};
        List<String> abcFilter1 = Arrays.stream(abc1)
                .distinct()
                .collect(Collectors.toList());
        abcFilter1.stream().forEach(s -> System.out.println(s));

        titulo("allMatch, anyMatch, noneMatch");
        List<Integer> integerList = Arrays.asList(100,200,300,400);
        boolean allMatch = integerList.stream().allMatch(integer -> integer > 200);
        System.out.println(allMatch);

        boolean anyMatch = integerList.stream().anyMatch(integer -> integer > 200);
        System.out.println(anyMatch);

        boolean noneMatch = integerList.stream().allMatch(integer -> integer > 2000);
        System.out.println(noneMatch); //ningun elemento supera los 2000

        titulo("Sum Average range");
        setUpUser();
        double result = users.stream()
                .mapToInt(User::getId)
                .average()
                .orElse(0);
        System.out.println(result);

        result = users.stream()
                .mapToInt(User::getId)
                .sum();
        System.out.println(result);

        System.out.println(IntStream.range(0,100).sum());


    }

    private static void setUpUser(){
        users = new ArrayList<>();
        users.add(new User(1,"Carlos"));
        users.add(new User(2,"Pepito"));
        users.add(new User(3,"Jose"));
        users.add(new User(4,"Maria"));
        users.add(new User(5,"Pepito"));
        users.add(new User(6,"Noelia"));
    }

    private static void imprimirLista(){
        users.stream().forEach(user -> System.out.println(user.getId() + " " + user.getNombre()));
    }

    private static void titulo(String titulo){
        System.out.println("------------------------"+ titulo +"-------------------------------");
    }
}
