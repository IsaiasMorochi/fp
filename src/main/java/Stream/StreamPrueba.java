package Stream;

import javax.jws.soap.SOAPBinding;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Stream representa una secuencia de elementos
 * Stream son emboltorios alrededor de una fuente de datos y hacer su procesameinto masivo
 * Los Streams nos permiten realizar operaciones complejas sobre un conjunto de datos
 *
 * @since 1.8
 */
public class StreamPrueba {

    private static List<User> users;

    public static void main(String[] arg){
        setUpUser();

        Stream stream = Stream.of(users);
        users.stream();

        forEach();

        mapCollection();

        filters();

        findFirts();

        flatMap();

        peek();

        count();

        skipAndLimit();

        sorted();

        minMax();

        distinct();

        allMatchAnyMatchNoneMatch();

        sumAvgRange();

        reduce();

        joining();

        toSet();

        summarizingDouble();

        partitioningBy();

        groupingBy();

        mapping();

        streamParalelo();

    }

    /**
     * Recorrer elementos
     */
    private static void forEach() {
        titulo("forEach");
        users.stream().forEach(user -> user.setNombre(user.getNombre() + " Apellido"));
        imprimirLista();
    }

    /**
     * Pasar una lista a un map
     */
    private static void mapCollection() {
        titulo("Map y Collectors.toList");
        List<String> list = users.stream()
                .map(user -> user.getNombre())
                .collect(Collectors.toList());
        list.stream().forEach(s -> System.out.println(s));
    }

    /**
     * Filters permite asignar una clausula de filtro
     */
    private static void filters() {
        titulo("Filters");
        setUpUser();
        List<User> usersFilters = users.stream()
                .filter(user -> user.getNombre() != "Pepito")
                .filter(user -> user.getId() < 3)
                .collect(Collectors.toList());
        usersFilters.stream().forEach(user -> System.out.println(user.getId() + " " + user.getNombre()));
    }

    /**
     * finFirts encontrar el primero
     */
    private static void findFirts() {
        titulo("Find Firts");
        setUpUser();
        User user = users.stream()
                .filter(e -> e.getNombre().equals("Pepito"))
                .findFirst()
                .orElse(null);
        System.out.println(user.getId() + " " + user.getNombre());
    }

    private static void flatMap() {
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

    /**
     * Es una similar a forEach sin ser una funcion final
     * nos permite realizar mas acciones
     */
    private static void peek() {
        titulo("Peek");
        setUpUser();
        List<User> userList = users.stream()
                .peek(user1 -> user1.setNombre(user1.getNombre() + " Apellido"))
                .collect(Collectors.toList());
        userList.stream().forEach(user1 -> System.out.println(user1.getNombre()));
    }

    /**
     * Count cuenta cantidad de elementos
     */
    private static void count() {
        titulo("Count");
        setUpUser();
        long numeroFiltrado = users.stream()
                .filter(user1 -> user1.getId() < 3)
                .count();
        System.out.println(numeroFiltrado);
    }

    /**
     * Skip Realiza un salto de elemento
     * Limit limita un numero de elementos
     */
    private static void skipAndLimit() {
        titulo("Skip y Limit");
        String[] abc = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
        List<String> abcFilter = Arrays.stream(abc)
                .skip(2)  //salta los elementos
                .limit(4) //los limita solo a 4 elementos
                .collect(Collectors.toList());
        abcFilter.stream().forEach(s -> System.out.println(s));
    }

    /**
     * Ordena los elementos de una lista
     */
    private static void sorted() {
        titulo("Sorted");
        setUpUser();
        users = users.stream()
                .sorted(Comparator.comparing(User::getNombre))
                .collect(Collectors.toList());
        imprimirLista();
    }

    /**
     * Min - Max
     */
    private static void minMax() {
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
    }

    /**
     * Elimina los elementos repetidos
     */
    private static void distinct() {
        titulo("Distinct");
        String[] abc1 = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j","a","b"};
        List<String> abcFilter1 = Arrays.stream(abc1)
                .distinct()
                .collect(Collectors.toList());
        abcFilter1.stream().forEach(s -> System.out.println(s));
    }

    /**
     *
     */
    private static void allMatchAnyMatchNoneMatch() {
        titulo("allMatch, anyMatch, noneMatch");
        List<Integer> integerList = Arrays.asList(100,200,300,400);
        boolean allMatch = integerList.stream().allMatch(integer -> integer > 200);
        System.out.println(allMatch);

        boolean anyMatch = integerList.stream().anyMatch(integer -> integer > 200);
        System.out.println(anyMatch);

        boolean noneMatch = integerList.stream().allMatch(integer -> integer > 2000);
        System.out.println(noneMatch); //ningun elemento supera los 2000
    }

    /**
     * Sum - Avg - Range
     */
    private static void sumAvgRange() {
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

    /**
     * la operación de reduce aplica un operador binario a cada elemento de la secuencia
     * donde el primer argumento para el operador es el valor de retorno de la aplicación
     * anterior y el segundo argumento es el elemento de la secuencia actual.
     */
    private static void reduce() {
        titulo("Reduce");
        setUpUser();
        int numero = users.stream()
                .map(User::getId)
                .reduce(0, Integer::sum); //identity : valor por el que comienza
        System.out.println(numero);
    }

    /**
     * Realiza en paralelo una funcion.
     */
    private static void streamParalelo() {
        titulo("Stream Paralelo");
        setUpUser();

        long tiempo1 = System.currentTimeMillis();
        users.stream().forEach(e -> convertirAMayusculas(e.getNombre()));
        long tiempo2 = System.currentTimeMillis();
        System.out.println("Normal: " + (tiempo2 - tiempo1));

        tiempo1 = System.currentTimeMillis();
        users.parallelStream().forEach(e -> convertirAMayusculas(e.getNombre()));
        tiempo2 = System.currentTimeMillis();
        System.out.println("Paralelo: " + (tiempo2 - tiempo1));

    }

    private static String  convertirAMayusculas(String nombre){
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return nombre.toUpperCase();
    }

    /**
     * Convierte en una lista
     */
    private static void mapping() {
        titulo("mapping");
        setUpUser();
        List<String> personas = users.stream()
                .collect(Collectors.mapping(User::getNombre, Collectors.toList()));
        personas.stream().forEach(e -> System.out.println(e));
    }

    /**
     * Realiza una agrupacion de acuerdo a una clausula
     */
    private static void groupingBy() {
        titulo("groupingBy");
        setUpUser();
        Map<Character, List<User>> grupoAlfabetico = users.stream()
                .collect(Collectors.groupingBy(user -> new Character(user.getNombre().charAt(0))));
        grupoAlfabetico.get('C').stream().forEach(e -> System.out.println(e.getNombre()));
        grupoAlfabetico.get('M').stream().forEach(e -> System.out.println(e.getNombre()));
        grupoAlfabetico.get('P').stream().forEach(e -> System.out.println(e.getNombre()));
    }

    /**
     * De una lista de elementos devuelve dos listas
     * una que se cumple el predicado y
     * otra en la que no se cumple el predicado
     */
    private static void partitioningBy() {
        titulo("partitioningBy");
        setUpUser();
        List<Integer> listNumbers = Arrays.asList(5,7,34,56,2,3,67,4,98);
        Map<Boolean, List<Integer>> esMayor = listNumbers.stream()
                .collect(Collectors.partitioningBy(o -> o > 10));
        esMayor.get(true).stream().forEach(i -> System.out.println(i));
        esMayor.get(false).stream().forEach(i -> System.out.println(i));
    }

    /**
     * Permite sacar estadisitcas de una lista
     * Avg - Max - Min - Count - Sum
     */
    private static void summarizingDouble() {
        titulo("summarizingDouble");
        setUpUser();
        DoubleSummaryStatistics summaryStatistics = users.stream()
                .collect(Collectors.summarizingDouble(User::getId));
        System.out.println(summaryStatistics.getAverage() + " " + summaryStatistics.getMax() + " " +
                summaryStatistics.getMin() + " " + summaryStatistics.getCount() + " " + summaryStatistics.getSum() );

        DoubleSummaryStatistics summaryStatistics1 = users.stream()
                .mapToDouble(User::getId)
                .summaryStatistics();
        System.out.println(summaryStatistics1.getAverage() + " " + summaryStatistics1.getMax() + " " +
                summaryStatistics1.getMin() + " " + summaryStatistics1.getCount() + " " + summaryStatistics1.getSum() );
    }


    /**
     * Permite concatenar por un delimitador o String
     */
    private static void joining() {
        titulo("Joining");
        setUpUser();
        String names = users.stream()
                .map(User::getNombre)
                .collect(Collectors.joining("|"))
                .toString();
        System.out.println(names);
    }

    /**
     * Devuelve un colector de los elementos de entrada
     * nos garantiza que no habra elementos repetidos
     */
    private static void toSet() {
        titulo("toSet");
        setUpUser();
        Set<String> setNames = users.stream()
                .map(User::getNombre)
                .collect(Collectors.toSet());
        setNames.stream().forEach(System.out::println);
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
