package _01_multithreading._12_inter_thread_communication;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerWithCondition {
    private static int data;
    private static boolean isDataAvailable = false;

    private static final Lock lock = new ReentrantLock();

    private static final Condition canProduce = lock.newCondition();
    private static final Condition canConsume = lock.newCondition();

    public static void main(String[] args) {

        Thread producer = new Thread(()->{
            for (int i = 1; i <= 5; i++) {
                produce(i);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });


        Thread consumer = new Thread(()->{
            for (int i = 1; i <= 5; i++) {
                consume();

                try {
                    Thread.sleep(1200);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        producer.start();
        consumer.start();
    }

    public static void produce(int value){
        lock.lock();
        try{
            // if data is available - wait
            while (isDataAvailable){
                canProduce.await();
            }
            data = value;
            isDataAvailable = true;
            System.out.println("Produced: "+data);

            canConsume.signal(); // Notify consumer

        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }


    }

    public static void consume(){
        lock.lock();
        try{
            // if no data - wait
            while(!isDataAvailable){
                canConsume.await();
            }

            // otherwise consume
            System.out.println("Consumed: "+data);
            isDataAvailable = false;
            canProduce.signal(); // Notify Producer

        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
