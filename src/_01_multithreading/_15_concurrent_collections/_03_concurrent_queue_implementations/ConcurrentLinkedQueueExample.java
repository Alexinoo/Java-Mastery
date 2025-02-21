package _01_multithreading._15_concurrent_collections._03_concurrent_queue_implementations;

import java.util.concurrent.ConcurrentLinkedQueue;

public class ConcurrentLinkedQueueExample {

    public static void main(String[] args) {

        ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<>();

        queue.offer(10);
        queue.offer(20);

        System.out.println("Polled: "+ queue.poll()); // removes 10

        System.out.println("Remaining: "+queue);

    }
}
