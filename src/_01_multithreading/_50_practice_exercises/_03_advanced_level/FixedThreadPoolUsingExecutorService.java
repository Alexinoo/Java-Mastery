package _01_multithreading._50_practice_exercises._03_advanced_level;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * Implement a Fixed-Size Thread Pool using ExecutorService
 */
public class FixedThreadPoolUsingExecutorService implements Runnable{

    private int taskId;

    public FixedThreadPoolUsingExecutorService(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" is running.. "+ taskId);

        // simulate some delay to run the task
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(taskId+ " executed successfully!");
    }

    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(2);

        for (int i = 1; i <= 5; i++) {
            executor.execute(new FixedThreadPoolUsingExecutorService(i));
        }


        executor.shutdown();
    }
}
