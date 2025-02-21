package _01_multithreading._09_volatile_keyword;

public class WithVolatileKeyword {
    private static volatile boolean running = true;

    public static void main(String[] args) throws InterruptedException {
        Thread worker = new Thread(() -> {
            while (running) {  // Always reads from main memory
            }
            System.out.println("Worker thread stopped.");
        });
        worker.start();

        Thread.sleep(1000);
        running = false;  // Immediately visible to worker thread
        System.out.println("Main thread updated running to false.");
    }
}