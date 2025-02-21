package _01_multithreading._10_volatile_vs_synchronized;

public class VolatileVsSynchronized {

    private volatile int counterVolatile = 0;
    private int counterSync = 0;

    public void incrementVolatile(){
        counterVolatile++;
    }

    public synchronized void incrementSync(){
        counterSync++;
    }

    public int getCounterVolatile() {
        return counterVolatile;
    }

    public int getCounterSync() {
        return counterSync;
    }

    public static void main(String[] args) throws InterruptedException {

        VolatileVsSynchronized demo = new VolatileVsSynchronized();

        // volatile example
        Thread thread1 = new Thread(()->{
            for (int i = 1; i <= 1000 ; i++) {
                demo.incrementVolatile();
            }
        });

        Thread thread2 = new Thread(()->{
            for (int i = 1; i <= 1000 ; i++) {
                demo.incrementVolatile();
            }
        });

        thread1.start();
        thread2.start();


        thread1.join();
        thread2.join();

        System.out.println("Volatile counter: "+demo.getCounterVolatile()); // May not be 2000

        // synchronized example

        Thread thread3  = new Thread(()->{
            for (int i = 1; i <= 1000 ; i++) {
                demo.incrementSync();
            }
        });

        Thread thread4  = new Thread(()->{
            for (int i = 1; i <= 1000 ; i++) {
                demo.incrementSync();
            }
        });

        thread3.start();
        thread4.start();


        thread3.join();
        thread4.join();

        System.out.println("Synchronized counter: "+demo.getCounterSync()); // Will be 2000
    }
}














