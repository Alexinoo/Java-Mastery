package _01_multithreading._50_practice_exercises._03_advanced_level;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UseCountDownLatchWithMultipleThreads extends Thread{
    private final CountDownLatch latch;

    public UseCountDownLatchWithMultipleThreads(CountDownLatch latch, String name) {
        super(name);
        this.latch = latch;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" is running..");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" finished");
        latch.countDown();
    }

    public static void main(String[] args) {
        int numWorkers = 3;
        CountDownLatch latch = new CountDownLatch(numWorkers);

        ExecutorService executor = Executors.newFixedThreadPool(2);

        for (int i = 1; i <= 3; i++) {
            final int taskId = i;
            executor.execute(()->{
                new UseCountDownLatchWithMultipleThreads(latch,"Worker-Thread-"+taskId).start();
            });
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All tasks executed...");

        executor.shutdown();
    }
}
