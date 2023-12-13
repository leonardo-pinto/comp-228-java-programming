package programming;

import java.util.List;

public class Main {
    public static void main(String[] args) {

//        printAllNumbersInListStructured(List.of(1, 5, 4, 9, 10, 1));
//        printAllNumbersInListFunctional(List.of(1, 5, 4, 9, 10, 1));

        List<String> values = List.of("Spring", "Spring Boot", "API", "Microservices", "AWS", "Docker", "Kubernetes");

//        printSpringValues(values);
        printBasedOnValueLength(values, 4);
//        printEvenNumbersInListFunctional(List.of(1, 5, 4, 9, 10, 1));
    }

    private static void printSpringValues(List<String> values) {
        values.stream().filter(value -> value.contains("Spring")).forEach(System.out::println);
    }

    private static void printBasedOnValueLength(List<String> values, int length) {
        values.stream().filter(value -> value.length() >= length).forEach(System.out::println);
    }

    // print courses containing "Spring"
    // pring courses whose name has 4 letters at least

    private static void printEvenNumbersInListFunctional(List<Integer> numbers) {
        numbers.stream().filter(number -> number%2 == 0).forEach(System.out::println);
    }

    private static void printAllNumbersInListFunctional(List<Integer> numbers) {
        numbers.forEach(System.out::println);

    }

    private static void printAllNumbersInListStructured(List<Integer> numbers) {
        for (int number : numbers) {
            System.out.println(number);
        }
    }
}
