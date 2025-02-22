package _01_multithreading._50_practice_exercises._01_beginner_level;

/*
 * Create a thread that prints the current thread name.
 */
public class PrintCurrentThreadName implements Runnable{
    @Override
    public void run() {
        System.out.println("Name of the current thread: "+Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        Thread printThread = new Thread(new PrintCurrentThreadName());
        printThread.start();
    }
}
