package _01_multithreading._50_practice_exercises._03_advanced_level;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

/*
 * Use a CountDownLatch to make multiple threads wait before continuing.
 *
 * How It Works
 * ✅ All runners wait at the starting line (latch.await()).
 * ✅ Main thread simulates a countdown (Thread.sleep(3000)).
 * ✅ Race starts when latch.countDown() is called, allowing all waiting threads to continue.
 */

class Runner implements Runnable {

    private final int id;
    private final CountDownLatch latch;

    public Runner(int id, CountDownLatch latch) {
        this.id = id;
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            System.out.println("Runner " + id + " is ready at the start line.");
            latch.await(); // wait for the countdown to reach zero
            System.out.println("Runner "+ id + " has started running!");
            Thread.sleep(ThreadLocalRandom.current().nextInt(1000,3000));
            System.out.println("Runner "+ id + " has finished the race!");

        }catch(InterruptedException e){
             Thread.currentThread().interrupt();

        }
    }
}
class CountDownLatchExample {
    public static void main(String[] args) throws InterruptedException {

        int numOfRunners = 5;
        CountDownLatch latch = new CountDownLatch(1); // Latch starts at 1 (start signal)

        for (int i = 1; i <= numOfRunners ; i++) {
            new Thread(new Runner(i, latch)).start();
        }

        Thread.sleep(3000); // Simulate race preparation

        System.out.println("Ready... Set... Go!");
        latch.countDown();      // Releases all waiting runners

    }
}
