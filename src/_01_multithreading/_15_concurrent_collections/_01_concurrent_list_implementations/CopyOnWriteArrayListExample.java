package _01_multithreading._15_concurrent_collections._01_concurrent_list_implementations;

import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListExample {

    public static void main(String[] args) {

        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();

        list.add("Nairobi");
        list.add("Tokyo");
        list.add("Denver");

        // Iterating while modifying (No ConcurrentModificationException)
        for (String name : list){
            System.out.println(name);
            list.add("Moscow");         // safe modification
        }


        System.out.println("Final list: "+list); // Final list: [Nairobi, Tokyo, Denver, Moscow, Moscow, Moscow]

    }
}
