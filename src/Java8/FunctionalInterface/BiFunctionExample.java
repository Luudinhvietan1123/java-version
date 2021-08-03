package Java8.FunctionalInterface;

import com.sun.deploy.net.HttpRequest;
import com.sun.deploy.net.HttpResponse;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import sun.net.www.http.HttpClient;

import java.net.URI;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class BiFunctionExample {
    public static void main(String[] args) {
        System.out.println("\nTakes two Integers and returns an Integer, Double or List");
        BiFunction<Integer, Integer, Integer> func = (x1, x2) -> x1 + x2;
        Integer result = func.apply(2, 3);
        System.out.println(result);
        BiFunction<Integer, Integer, Double> func2 = (x1, x2) -> Math.pow(x1, x2);
        Double result2 = func2.apply(2, 4);
        System.out.println(result2);
        BiFunction<Integer, Integer, List<Integer>> func3 = (x1, x2) -> Arrays.asList(x1 + x2);
        List<Integer> result3 = func3.apply(2, 3);
        System.out.println(result3);


        System.out.println("\nTakes two Integer and returns a Double, " +
                "and uses andThen() to chain it with a Function " +
                "to convert the Double into a String.");
        BiFunction<Integer, Integer, Double> func1 = (a1, a2) -> Math.pow(a1, a2);
        Function<Double, String> func4 = (input) -> "Result : " + String.valueOf(input);
        String result4 = func1.andThen(func4).apply(2, 4);
        System.out.println(result4);


        System.out.println("\nConverts the above program into a method " +
                "that accepts BiFunction and Function as arguments and chains it together.");
        String result5 = powToString(2, 4,
                (a1, a2) -> Math.pow(a1, a2),
                (r) -> "Result : " + String.valueOf(r));
        System.out.println(result5);


        System.out.println("\nConverts the above method into a generic method");
        // Take two Integers, pow it into a Double, convert Double into a String.
        String result6 = convert(2, 4,
                (a1, a2) -> Math.pow(a1, a2),
                (r) -> "Pow : " + String.valueOf(r));
        System.out.println(result6);     // Pow : 16.0
        // Take two Integers, multiply into an Integer, convert Integer into a String.
        String result7 = convert(2, 4,
                (a1, a2) -> a1 * a1,
                (r) -> "Multiply : " + String.valueOf(r));
        System.out.println(result7);    // Multiply : 4
        // Take two Strings, join both, join "cde"
        String result8 = convert("a", "b",
                (a1, a2) -> a1 + a2,
                (r) -> r + "cde");      // abcde
        System.out.println(result8);
        // Take two Strings, join both, convert it into an Integer
        Integer result9 = convert("100", "200",
                (a1, a2) -> a1 + a2,
                (r) -> Integer.valueOf(r));
        System.out.println(result9);


        System.out.println("\nFiltering a List by some conditions.");
        BiFunctionExample obj = new BiFunctionExample();
        List<String> list = Arrays.asList("node", "c++", "java", "javascript");
        List<String> result10 = obj.filterList(list, 3, obj::filterByLength);
        System.out.println(result10);   // [node, java, javascript]

        List<String> result11 = obj.filterList(list, 3, (l1, size) -> {
            if (l1.length() > size) {
                return l1;
            } else {
                return null;
            }
        });
        System.out.println(result11);  // [node, java, javascript]

        List<String> result12 = obj.filterList(list, "c", (l1, condition) -> {
            if (l1.startsWith(condition)) {
                return l1;
            } else {
                return null;
            }
        });
        System.out.println(result12);  // [c++]

        List<Integer> number = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> result13 = obj.filterList(number, 2, (l1, condition) -> {
            if (l1 % condition == 0) {
                return l1;
            } else {
                return null;
            }
        });
        System.out.println(result13);  // [2, 4]
    }

    public String filterByLength(String str, Integer size) {
        if (str.length() > size) {
            return str;
        } else {
            return null;
        }
    }

    public <T, U, R> List<R> filterList(List<T> list1, U condition,
                                        BiFunction<T, U, R> func) {
        List<R> result = new ArrayList<>();

        for (T t : list1) {
            R apply = func.apply(t, condition);
            if (apply != null) {
                result.add(apply);
            }
        }
        return result;
    }

    public static <A1, A2, R1, R2> R2 convert(A1 a1, A2 a2,
                                              BiFunction<A1, A2, R1> func,
                                              Function<R1, R2> func2) {
        return func.andThen(func2).apply(a1, a2);
    }

    public static <R> R powToString(Integer a1, Integer a2,
                                    BiFunction<Integer, Integer, Double> func,
                                    Function<Double, R> func2) {
        return func.andThen(func2).apply(a1, a2);

    }
}
