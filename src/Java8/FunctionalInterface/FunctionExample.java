package Java8.FunctionalInterface;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.*;
import java.util.function.Function;

public class FunctionExample {
    public static void main(String[] args) {

        System.out.println("\nTake <T> String and return length of string as <R> Integer");
        Function<String, Integer> func = x -> x.length();
        Integer apply = func.apply("Oracle");   // 6
        System.out.println(apply);


        System.out.println("\nChains the Function with andThen()");
        Function<String, Integer> func1 = x -> x.length();
        Function<Integer, Integer> func2 = x -> x * 2;
        Integer result = func1.andThen(func2).apply("IntelliJ IDEA");   // 12
        System.out.println(result);


        System.out.println("\nAccepts Function as an argument, convert a List into a Map.");
        FunctionExample obj = new FunctionExample();
        List<String> list = Arrays.asList("node", "c++", "java", "javascript");
        Map<String, Integer> map = obj.convertListToMap(list, x -> x.length());
        System.out.println(map);
        Map<String, Integer> map2 = obj.convertListToMap(list, obj::getLength);
        System.out.println(map2);


        System.out.println("\nAccepts Function as an argument, convert a List of String into another List of String, which was hashed with SHA256.");
        FunctionExample obj2 = new FunctionExample();
        List<String> list1 = Arrays.asList("node", "c++", "java", "javascript");
        List<String> result1 = obj.map(list, obj::sha256);
        result1.forEach(System.out::println);
    }

    public <T, R> List<R> map(List<T> list, Function<T, R> func) {
        List<R> result = new ArrayList<>();
        for (T t : list) {
            result.add(func.apply(t));
        }
        return result;
    }

    public String sha256(String str) {
        return DigestUtils.sha256Hex(str);
    }

    public <T, R> Map<T, R> convertListToMap(List<T> list, Function<T, R> func) {
        Map<T, R> result = new HashMap<>();
        for (T t : list) {
            result.put(t, func.apply(t));
        }
        return result;
    }

    public Integer getLength(String str) {
        return str.length();
    }
}
