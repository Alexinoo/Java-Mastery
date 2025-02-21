package _01_multithreading._16_concurrent_collections_use_cases;

import java.util.concurrent.*;

public class OrderProcessor {

    private final ConcurrentLinkedQueue<String> orders  = new ConcurrentLinkedQueue<>();

    public void placeOrder(String order){
        orders.offer(order);
        System.out.println(Thread.currentThread().getName() + " placed order: " + order);
    }

    public void processOrders(){
        while (!orders.isEmpty()){
            String order = orders.poll();
            System.out.println(Thread.currentThread().getName() + " processing order: " + order);
        }
    }
}

class Ecommerce {
    public static void main(String[] args){

        OrderProcessor processor = new OrderProcessor();
        int totalOrders = 5;
        CountDownLatch latch = new CountDownLatch(totalOrders);


        // Create a fixed thread pool for placing orders
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Submit order placement tasks
        for (int i = 1; i <= totalOrders ; i++) {
            final int orderId = i;
            executor.submit(()->{
                processor.placeOrder("Order "+ orderId);
                latch.countDown();  // Decrement latch count
            });
        }


        // ScheduledExecutorService for delayed processing
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        try {
            latch.await();          // Wait until all orders are placed
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Delay processing by 2 seconds
        scheduler.schedule(()->{
            System.out.println("\n----Delayed Order Processing Starts ---");
            processor.processOrders();
        }, 2 , TimeUnit.SECONDS);

        // Shutdown executors
        executor.shutdown();
        scheduler.shutdown();

        ////////////////////////////////   Old Way        //////////////////////////////////////////////////////////////

        /* simulating multiple threads adding orders
        // Creating threads manually
        Thread orderThread1 = new Thread(()-> processor.placeOrder("Order 1"));
        Thread orderThread2 = new Thread(()-> processor.placeOrder("Order 2"));
        Thread orderThread3 = new Thread(()-> processor.placeOrder("Order 3"));

        try {
            orderThread1.start();
            orderThread1.join();

            orderThread2.start();
            orderThread2.join();

            orderThread3.start();
            orderThread3.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        Thread.sleep(2000);

        processor.processOrders(); */
    }
}















