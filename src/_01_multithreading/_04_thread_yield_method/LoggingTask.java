package _01_multithreading._04_thread_yield_method;

public class LoggingTask extends Thread{
    @Override
    public void run() {
        for (int i = 0; i <= 5 ; i++) {
            System.out.println(Thread.currentThread().getName() +" - Logging data "+i);

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            Thread.yield();
        }
    }
}


class TransactionTask extends Thread {
    @Override
    public void run() {
        for (int i = 0; i <= 5; i++) {
            System.out.println(Thread.currentThread().getName()+ " - Processing Transaction");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class ThreadYieldExample{
    public static void main(String[] args) {
        LoggingTask loggingThread = new LoggingTask();
        TransactionTask transactionThread = new TransactionTask();

        loggingThread.setPriority(Thread.MIN_PRIORITY);         // low priority
        transactionThread.setPriority(Thread.MAX_PRIORITY);     // high priority


        loggingThread.start();
        transactionThread.start();
    }
}