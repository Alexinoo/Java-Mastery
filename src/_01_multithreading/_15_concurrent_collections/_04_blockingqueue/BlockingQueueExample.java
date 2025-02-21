package _01_multithreading._15_concurrent_collections._04_blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/*
 * Test with ArrayBlockingQueue and LinkedBlockingQueue implementations
 *
 */

public class BlockingQueueExample {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(2);
        //BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(2);

        // producer Thread
       Thread producer =  new Thread(()->{
                try {
                    for (int i = 1; i <= 3 ; i++) {
                        queue.put(i);
                        System.out.println("Produced: "+i);
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
        });

        // consumer Thread
        Thread consumer = new Thread(()->{
                try {
                    for (int i = 1; i <= 3; i++) {
                        Thread.sleep(2000);
                        System.out.println("Consumed: "+queue.take());
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
        });

        producer.start();
        consumer.start();


    }
}
