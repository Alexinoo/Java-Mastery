package _01_multithreading._50_practice_exercises._02_intermediate_level;

import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

/*
 * Create a thread-safe cache using ConcurrentHashMap
 */

public class ThreadSafeCache<K,V>{

//    private final ConcurrentHashMap<K,V> cache = new ConcurrentHashMap<>();
    private final ConcurrentSkipListMap<K,V> cache = new ConcurrentSkipListMap<>();

    public void put(K key, V value){
        cache.put(key,value);
        System.out.println(Thread.currentThread().getName() + " added " + key + " -> "+ value);
    }

    public V get(K key){
        return cache.get(key);
    }

    public void remove(K key){
        cache.remove(key);
        System.out.println(Thread.currentThread().getName() + " removed "+key);
    }

    public boolean containsKey(K key){
        return cache.containsKey(key);
    }

    public void printContents(){
        System.out.println("\n-- Cache contents---");
        for (Map.Entry<K,V> entry : cache.entrySet()){
            System.out.println(entry.getKey() +" -> " +entry.getValue());
        }
    }

    public static void main(String[] args){
        ThreadSafeCache<String,String> cache = new ThreadSafeCache<>();

        Runnable writerTask = ()-> {
            for (int i = 1; i <= 5; i++) {
                cache.put("Key"+i , "Value"+i);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Runnable readerTask = ()->{
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() +" read: Key"+i+ " -> "+ cache.get("Key"+i));
                try {
                    Thread.sleep(400);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        Thread writer1 = new Thread(writerTask,"Writer-Thread-1");
        Thread writer2 = new Thread(writerTask,"Writer-Thread-2");
        Thread reader = new Thread(readerTask,"Reader-Thread-1");

        writer1.start();
        writer2.start();
        reader.start();

        try {
            writer1.join();
            writer2.join();
            reader.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        cache.printContents();
    }
}
