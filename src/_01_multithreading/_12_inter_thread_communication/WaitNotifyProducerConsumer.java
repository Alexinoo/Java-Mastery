package _01_multithreading._12_inter_thread_communication;

public class WaitNotifyProducerConsumer {
    private int data;
    private boolean isDataAvailable = false;

    // producer method
    public synchronized void produce(int value)  {
        while (isDataAvailable){         // if data is available, wait for the consumer to consume
            try{
                wait();                  // release the lock
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        data = value;               // update/store the produced data
        System.out.println("Produced: "+ value);
        isDataAvailable = true;         // mark the data as available
        notify();                   // notify the consumer thread
    }


    // consumer method
    public synchronized void consume() {
        while (!isDataAvailable){           // if no data, wait for producer
            try{
                    wait();                     // Release the lock and wait for the producer

            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        System.out.println("Consumed: "+data);
        isDataAvailable = false;
        notify();                       // Notify the waiting producer thread
    }
}


class WaitNotifyProducerConsumerExample {
    public static void main(String[] args){
        WaitNotifyProducerConsumer shared = new WaitNotifyProducerConsumer();

        // Producer Thread
        Thread producer = new Thread(()->{
            for (int i = 1; i <= 5 ; i++) {
                shared.produce(i);
                try {
                    Thread.sleep(1000);            // simulate some delay
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });


        // Consumer Thread
        Thread consumer = new Thread(()->{
            for (int i = 1; i <= 5 ; i++) {
                shared.consume();
                try {
                    Thread.sleep(1200);            // simulate some delay
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        producer.start();
        consumer.start();

    }
}
