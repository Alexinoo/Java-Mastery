package _01_multithreading._50_practice_exercises._01_beginner_level;

/*
 * Create a thread that runs in an infinite loop until interrupted.
 */
public class InfiniteLoopThread extends Thread{
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()){
            System.out.println("Thread is running...");
        }
        System.out.println("Thread interrupted, stopping....");
    }
}

class InterruptExample {
    public static void main(String[] args) {
        InfiniteLoopThread thread = new InfiniteLoopThread();
        thread.start();

        try {
            Thread.sleep(3000); // Let it run for 3 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread.interrupt(); // Interrupt the thread

    }
}
