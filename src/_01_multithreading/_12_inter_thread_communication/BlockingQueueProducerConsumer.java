package _01_multithreading._12_inter_thread_communication;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


class ProducerThread implements Runnable{
    private BlockingQueue<Integer> queue;
    public ProducerThread(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 5 ; i++) {
                    System.out.println("Produced: "+i);
                    queue.put(i);
                    Thread.sleep(1000);
            }
            queue.put(-1);
        }catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }
}

class ConsumerThread implements Runnable{
    private BlockingQueue<Integer> queue;
    public ConsumerThread(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
           while (true){
               int data = queue.take();
               if (data == -1) {
                   break;
               }
               System.out.println("Consumed: "+data);
               Thread.sleep(1200);
           }
        }catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }
}

public class BlockingQueueProducerConsumer {

    public static void main(String[] args)  {

        // 1. Implementation of BlockingQueue with LinkedBlockingQueue - Optionally bounded, linked-node-based queue.
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(3);

        // 2. Implementation of BlockingQueue with ArrayBlockingQueue - Bounded, fixed-size array-based queue.
        // BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(2);

        // 3. Implementation of BlockingQueue with PriorityBlockingQueue - Unbounded, priority-based queue. (in any order)
       //  BlockingQueue<Integer> queue = new PriorityBlockingQueue<>(2);

        // 3. Implementation of BlockingQueue with Synchronous - A queue of size 0, where each put operation must wait for a take operation.
       // BlockingQueue<Integer> queue = new SynchronousQueue<>();

        Thread producer = new Thread(new ProducerThread(queue));
        Thread consumer = new Thread(new ConsumerThread(queue));

        producer.start();
        consumer.start();


    }
}
