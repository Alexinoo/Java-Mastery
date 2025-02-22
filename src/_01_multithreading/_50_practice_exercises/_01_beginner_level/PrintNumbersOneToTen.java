package _01_multithreading._50_practice_exercises._01_beginner_level;

/*
 * Print numbers from 1 to 10 in a separate thread.
 */
public class PrintNumbersOneToTen implements Runnable{
    @Override
    public void run() {
        for (int i = 1; i <= 10 ; i++) {
            System.out.println(i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        Thread printThread = new Thread(new PrintNumbersOneToTen());
        printThread.start();
    }
}
