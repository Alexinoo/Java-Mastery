package _01_multithreading._50_practice_exercises._01_beginner_level;

/*
 * Create multiple threads and observe how they execute in parallel.
 */
public class MultipleThreadsExecuteInParallel {

    public static void main(String[] args) {

        Thread thread1 = new Thread(()-> System.out.println("Thread-1 running..."));
        Thread thread2 = new Thread(()-> System.out.println("Thread-2 running..."));
        Thread thread3 = new Thread(()-> System.out.println("Thread-3 running..."));

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
