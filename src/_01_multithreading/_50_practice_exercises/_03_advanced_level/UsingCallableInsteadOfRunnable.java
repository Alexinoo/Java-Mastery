package _01_multithreading._50_practice_exercises._03_advanced_level;

import java.util.concurrent.*;

public class UsingCallableInsteadOfRunnable implements Callable<String> {
    @Override
    public String call() throws Exception {

        Thread.sleep(500);

        return "Task Executed by: "+ Thread.currentThread().getName();
    }

    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(1);

        Future<String> future = executor.submit(new UsingCallableInsteadOfRunnable());

        try {
            System.out.println(future.get());
        } catch (InterruptedException | ExecutionException e) {

           e.printStackTrace();
        }

        executor.shutdown();
    }
}
