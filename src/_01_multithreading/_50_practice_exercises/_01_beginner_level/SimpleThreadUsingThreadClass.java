package _01_multithreading._50_practice_exercises._01_beginner_level;

/*
 * Create and start a simple thread using the Thread class.
 */
public class SimpleThreadUsingThreadClass extends Thread {
    @Override
    public void run() {
        System.out.println("Created a simple thread using the Thread Class.");
    }
    public static void main(String[] args) {
        SimpleThreadUsingThreadClass simpleThread  = new SimpleThreadUsingThreadClass();

        simpleThread.start();
    }
}
