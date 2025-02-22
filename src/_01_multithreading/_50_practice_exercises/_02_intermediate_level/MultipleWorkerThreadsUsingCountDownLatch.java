package _01_multithreading._50_practice_exercises._02_intermediate_level;

import java.util.concurrent.CountDownLatch;

public class MultipleWorkerThreadsUsingCountDownLatch extends Thread{
    private CountDownLatch latch;

    public MultipleWorkerThreadsUsingCountDownLatch(CountDownLatch latch, String name) {
        super(name);
        this.latch = latch;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+ " is working");

        // simulate some delay in completing the task
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(Thread.currentThread().getName()+ " finished");
        latch.countDown();
    }

    public static void main(String[] args) {
        int numWorkers = 3;
        CountDownLatch latch = new CountDownLatch(numWorkers);

        for (int i = 1; i <= numWorkers; i++) {
            new MultipleWorkerThreadsUsingCountDownLatch(latch,"Worker-"+i).start();
        }

        //wait for all workers to finish

        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

         System.out.println("All workers have finished. Proceeding to the next step...");
    }
}
