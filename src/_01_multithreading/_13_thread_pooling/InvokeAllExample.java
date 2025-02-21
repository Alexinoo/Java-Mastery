package _01_multithreading._13_thread_pooling;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class InvokeAllExample {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ExecutorService executor = Executors.newFixedThreadPool(3);

        List<Callable<String>> tasks = Arrays.asList(
                ()-> "Task 1 completed",
                ()-> "Task 2 completed",
                ()-> "Task 3 completed"
        );

        List<Future<String>> results = executor.invokeAll(tasks);

        for (Future<String> future : results){
            System.out.println(future.get());
        }

        executor.shutdown();
    }
}
