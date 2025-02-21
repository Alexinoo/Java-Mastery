package _01_multithreading._15_concurrent_collections._02_concurrent_map_implementations;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class ConcurrentHashMapExample {

    public static void main(String[] args) {

        ConcurrentHashMap<Integer,String> map = new ConcurrentHashMap<>();

        map.put(1,"Java");
        map.put(2,"Python");

        Function<Integer,String> functionPredicate = key -> "C++"; // Thread safe operation

        map.computeIfAbsent(3, functionPredicate);

        System.out.println(map);
    }
}
