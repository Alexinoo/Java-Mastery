package _01_multithreading._06_thread_without_synchronization;

public class Counter {
    private int count = 0;

    public void increment(){
        count++; // This is not atomic and can cause a race condition
    }

    public int getCount() {
        return count;
    }
}

class RaceConditionExample{
    public static void main(String[] args) {
        Counter counter = new Counter();

        // Two Threads modifying the shared
        Thread thread1 = new Thread(()->{
            for (int i = 1; i <= 1000; i++) {
                counter.increment();
            }
        });

        Thread thread2 = new Thread(()->{
            for (int i = 1; i <= 1000; i++) {
                counter.increment();
            }
        });


        thread1.start();
        thread2.start();

        try{
            thread1.join();
            thread2.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }



        System.out.println("Final count: "+counter.getCount()); // Should be 2000, but it may not be


    }
}
