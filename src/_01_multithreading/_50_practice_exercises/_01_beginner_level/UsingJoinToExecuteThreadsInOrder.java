package _01_multithreading._50_practice_exercises._01_beginner_level;

/*
 * Use join() to ensure one thread finishes before another starts.
 */
public class UsingJoinToExecuteThreadsInOrder implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" executing...");
    }

    public static void main(String[] args) {

        Thread thread1 = new Thread(new UsingJoinToExecuteThreadsInOrder(),"Thread-1");
        Thread thread2 = new Thread(new UsingJoinToExecuteThreadsInOrder(),"Thread-2");


        thread1.start();
        try {
            thread1.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        thread2.start();
    }
}
