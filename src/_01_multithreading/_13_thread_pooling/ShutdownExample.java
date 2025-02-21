package _01_multithreading._13_thread_pooling;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ShutdownExample {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executor = Executors.newFixedThreadPool(2);


        for (int i = 1; i <= 5; i++) {
            final int taskId = i;

            executor.execute(()->{
                System.out.println("Executing Task "+taskId);

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        executor.shutdown(); // Initiates shutdown, allowing existing tasks to finish

        if (executor.awaitTermination(5, TimeUnit.SECONDS)){
            executor.shutdownNow();         // Forces shutdown if tasks take too long
        }

        System.out.println("Executor service shut down..");
    }
}
