package _01_multithreading._50_practice_exercises._02_intermediate_level;

/*
 * Implement a producer-consumer pattern using wait() and notify().
 */
public class ProducerConsumerWaitNotify {
    private int data;
    private boolean isDataAvailable = false;

    public synchronized void produce(int value){
        while (isDataAvailable) {         // if there is data - wait
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        // otherwise - update data value
        data = value;
        System.out.println("Produced: " + data);
        isDataAvailable = true;
        notify();                       // notify consumer

    }

    public synchronized int consume(){
        while(!isDataAvailable){        // if no data - wait
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        // otherwise consume
        System.out.println("Consumed: "+data);
        isDataAvailable = false;
        notify();                     // notify consumer
        return data;
    }

    public static void main(String[] args) {
        ProducerConsumerWaitNotify shared = new ProducerConsumerWaitNotify();

        Thread producer = new Thread(()->{
            for (int i = 1; i <= 5; i++) {
                shared.produce(i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread consumer = new Thread(()->{
            for (int i = 1; i <= 5 ; i++) {
                shared.consume();
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
