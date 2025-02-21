package _01_multithreading._09_volatile_keyword;

public class WithoutVolatileKeyword {
    private static boolean running = true;

    public static void main(String[] args) throws InterruptedException {

        Thread worker = new Thread(() -> {
            while (running) {  // Might get cached, leading to an infinite loop
            }
            System.out.println("Worker thread stopped.");
        });
        worker.start();

        Thread.sleep(1000);
        running = false;  // Might not be visible to the worker thread
        System.out.println("Main thread updated running to false.");

    }

}
