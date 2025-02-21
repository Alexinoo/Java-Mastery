package _01_multithreading._16_concurrent_collections_use_cases;

import java.util.concurrent.ConcurrentLinkedQueue;

public class OrderProcessor {

    private final ConcurrentLinkedQueue<String> orders  = new ConcurrentLinkedQueue<>();

    public void placeOrder(String order){
        orders.offer(order);
        System.out.println("Order placed: "+order);
    }

    public void processOrders(){
        while (!orders.isEmpty()){
            String order = orders.poll();
            System.out.println("Processing order: "+order);
        }
    }
}

class Ecommerce {
    public static void main(String[] args) throws InterruptedException {

        OrderProcessor processor = new OrderProcessor();

        // simulating multiple threads adding orders
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

        processor.processOrders();

    }

}















