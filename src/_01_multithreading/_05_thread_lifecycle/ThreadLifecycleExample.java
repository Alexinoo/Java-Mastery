package _01_multithreading._05_thread_lifecycle;

public class ThreadLifecycleExample {

    public static void main(String[] args) {

        Thread thread = new Thread(()->{

            try {
                Thread.sleep(1000); // TIMED_WAITING
                synchronized (ThreadLifecycleExample.class){
                    ThreadLifecycleExample.class.wait();    // WAITING
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        System.out.println("State after creation: "+thread.getState()); // NEW

        thread.start();

        System.out.println("State after start(): "+ thread.getState()); // RUNNABLE

        try{

            Thread.sleep(500);
            System.out.println("State after sleep(500ms): "+ thread.getState()); // TIMED_WAITING

            synchronized (ThreadLifecycleExample.class){
                ThreadLifecycleExample.class.notify();      // Notify the waiting thread
            }

            Thread.sleep(1000);
            System.out.println("State after completion: "+ thread.getState()); // TERMINATED

        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}








