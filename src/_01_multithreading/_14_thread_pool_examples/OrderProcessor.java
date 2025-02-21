package _01_multithreading._14_thread_pool_examples;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
    1. Processing Orders in an E-commerce System
    Imagine an e-commerce application where multiple customers place orders, and each order needs to be processed independently.

    Implementation Using FixedThreadPool


    Explanation
    - We use Executors.newFixedThreadPool(3), meaning at most 3 orders are processed simultaneously.
    - If there are more orders, they are queued until a thread is available.
    - .shutdown() ensures all tasks finish execution before termination.
 */
public class OrderProcessor implements Runnable{

    private final int orderId;

    public OrderProcessor(int orderId) {
        this.orderId = orderId;
    }

    public int getOrderId() {
        return orderId;
    }

    @Override
    public void run() {
        System.out.println("Processing order #"+getOrderId()+" by "+Thread.currentThread().getName());
        // simulate processing time - 2 seconds
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Order #"+getOrderId()+ " processed");
    }
}

class EcommerceOrderProcessing {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3); // Max 3 threads


        // Generate 10 orders
        for (int i = 1; i <= 10; i++) {
            executorService.submit(new OrderProcessor(i));
        }

        executorService.shutdown();
    }
}
