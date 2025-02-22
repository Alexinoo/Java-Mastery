package _01_multithreading._50_practice_exercises._01_beginner_level;

/*
 * Set thread priority using setPriority() and observe execution order.
 */
public class SetThreadPriority {

    public static void main(String[] args) {

        Thread thread1 = new Thread(()-> System.out.println("Thread with the lowest priority running first..."));
        Thread thread2 = new Thread(()-> System.out.println("Thread with the highest priority running last..."));

        thread1.setPriority(Thread.MIN_PRIORITY);
        thread2.setPriority(Thread.MAX_PRIORITY);

        thread1.start();
        thread2.start();
    }
}
