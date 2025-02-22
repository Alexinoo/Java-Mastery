package _01_multithreading._50_practice_exercises._02_intermediate_level;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * Implement a thread pool using Executors.newFixedThreadPool().
 */
public class ThreadPoolUsingFixedThreadPool {

    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(3);

        for (int i = 1; i <= 5; i++) {
            int taskId = i;
            executor.execute(()-> System.out.println("Task "+ taskId+ " executed by "+Thread.currentThread().getName()));
        }

        executor.shutdown();
    }
}
