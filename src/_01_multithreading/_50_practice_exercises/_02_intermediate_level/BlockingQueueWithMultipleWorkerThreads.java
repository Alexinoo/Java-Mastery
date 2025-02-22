package _01_multithreading._50_practice_exercises._02_intermediate_level;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/*
 * Create a task queue using BlockingQueue and multiple worker threads.
 */
public class BlockingQueueWithMultipleWorkerThreads {
    private static final BlockingQueue queue = new ArrayBlockingQueue(2);
//    private static final BlockingQueue queue = new LinkedBlockingQueue(3);
    public static void main(String[] args) {

        Thread producer = new Thread(()->{
            try {
                for (int i = 1; i <= 3; i++) {
                    queue.put(i);
                    System.out.println("Produced: " + i);
                    Thread.sleep(500);
                }
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread consumer = new Thread(()->{
            try {
                while(!queue.isEmpty()){
                    Thread.sleep(1000);
                    System.out.println("Consumed: "+queue.take());
                }
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        producer.start();
        consumer.start();
    }
}
