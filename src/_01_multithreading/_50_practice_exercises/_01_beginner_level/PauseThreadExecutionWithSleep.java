package _01_multithreading._50_practice_exercises._01_beginner_level;

/*
 * Use Thread.sleep() to pause a thread for a few seconds.
 */
public class PauseThreadExecutionWithSleep implements Runnable{
    @Override
    public void run() {
        System.out.println("Printing some text in the next 2 seconds...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("This text was printed after 2 seconds...");
    }

    public static void main(String[] args) {
        Thread simpleThread = new Thread(new PauseThreadExecutionWithSleep());
        simpleThread.start();
    }
}
