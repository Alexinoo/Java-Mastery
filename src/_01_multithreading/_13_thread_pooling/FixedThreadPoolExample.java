package _01_multithreading._13_thread_pooling;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPoolExample {

    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(5);
//        ExecutorService executor = Executors.newSingleThreadExecutor();

        for (int i = 1; i <= 5 ; i++) {

            final int taskId = i;
            executor.execute(()->{
                System.out.println("Executing Task "+ taskId + " on "+Thread.currentThread().getName());

                try {
                    Thread.sleep(2000); // simulate some work
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        executor.shutdown(); // Gracefully shutdown after all tasks complete
    }
}
