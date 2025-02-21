package _01_multithreading._12_inter_thread_communication;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockConditionProducerConsumer {
    private int data;
    private boolean isDataAvailable = false;

    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    public void produce(int value){
        lock.lock();                        // Acquire the lock
       try{
           while (isDataAvailable){
               condition.await();          // Wait if the data is available for consumer to consume
           }

           data = value;
           System.out.println("Produced: "+value);
           isDataAvailable = true;
           condition.signal();              // Notify consumer thread

       }catch (InterruptedException e){
            e.printStackTrace();
       }finally {
           lock.unlock();                   // Release the lock
       }

    }

    public int consume(){
        lock.lock();

        try{
            // Wait if no data is available
            while (!isDataAvailable){
                condition.await();
            }

            // consume the data
            System.out.println("Consumed: "+data);
            isDataAvailable = false;
            condition.signal();     // Notify the producer

            return data;
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();          // release the lock
        }
        return -1;
    }
}


class LockConditionProducerConsumerExample{
    public static void main(String[] args) {
        LockConditionProducerConsumer shared  = new LockConditionProducerConsumer();

        // Producer Example
        Thread producer = new Thread(()->{

            for (int i = 1; i <= 5 ; i++) {
                shared.produce(i);

                // simulate some delay to produce each task
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        // Consumer Example
        Thread consumer = new Thread(()->{

            for (int i = 1; i <= 5 ; i++) {
                shared.consume();

                // simulate some delay to consume each task
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
}