package _01_multithreading._50_practice_exercises._01_beginner_level;

/*
 * Implement a thread that stops execution after 5 seconds.
 */
public class StopsExecutingAfterFiveSeconds {

    public static void main(String[] args) {

        new Thread(()->{
            long start = System.currentTimeMillis();
            while( (System.currentTimeMillis() - start) < 5000) {
                System.out.println("Thread running...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("Thread stopped after 5 seconds");
        }).start();
    }
}
