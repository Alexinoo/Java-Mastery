package _01_multithreading._50_practice_exercises._03_advanced_level;

import java.util.concurrent.*;

public class ExecuteMultipleCallables{
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executor = Executors.newFixedThreadPool(2);

        Callable<String> task1 = ()-> Thread.currentThread().getName()+ " running task 1..";
        Callable<String> task2= ()-> Thread.currentThread().getName()+ " running task 2..";
        Callable<String> task3 = ()-> Thread.currentThread().getName()+ " running task 3..";

        Future<String> results1 = executor.submit(task1);
        Future<String> results2 = executor.submit(task2);
        Future<String> results3 = executor.submit(task3);

        System.out.println(results1.get());
        System.out.println(results2.get());
        System.out.println(results3.get());

        executor.shutdown();

    }
}
