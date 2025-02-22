package _01_multithreading._50_practice_exercises._01_beginner_level;

/*
 * Create and start a thread using the Runnable interface.
 */
public class SimpleThreadUsingRunnable implements Runnable{

    @Override
    public void run() {
        System.out.println("Created and started a thread by implementing a Runnable interface...");
    }

    public static void main(String[] args) {
        Thread simpleThread = new Thread( new SimpleThreadUsingRunnable());

        simpleThread.start();
    }
}
