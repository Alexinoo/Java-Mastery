package _01_multithreading._20_semaphores;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

class SharedResource {
    private final Semaphore semaphore = new Semaphore(2);

    public void accessResource(String threadName){
        try{
            System.out.println(threadName + " is trying to acquire a permit...");
            semaphore.acquire(); // Acquire a permit

            System.out.println(threadName + " has acquired a permit and is using the resource...");
            Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 5000)); // Simulate work

            System.out.println(threadName + " is releasing the permit...");
            semaphore.release(); // Release the permit
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
public class SemaphoreExample {
    public static void main(String[] args) {
        SharedResource resource = new SharedResource();

        Runnable task = ()-> {
            String threadName = Thread.currentThread().getName();
            resource.accessResource(threadName);
        };

        // Creating multiple threads
        for (int i = 1; i <= 4; i++) {
            new Thread(task).start();
        }
    }
}
