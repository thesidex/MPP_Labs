package prob7.prob7b;

import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

class LambdaLibrary {
    public static final BiFunction<List<Employee>, Character, String> filterAndSortNames =
            (employees, startLetter) ->
                    employees.stream()
                            .filter(employee -> employee.salary > 100000)
                            .filter(employee -> employee.lastName.charAt(0) > startLetter)
                            .map(employee -> employee.firstName + " " + employee.lastName)
                            .sorted()
                            .collect(Collectors.joining(", "));
}
