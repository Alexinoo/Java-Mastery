package _01_multithreading._08_thread_with_synchronization_on_block;

public class Counter {
    private int count = 0;

    public  void increment(){
        synchronized (this) {
            count++; // Atomic operation
        }
    }

    public int getCount() {
        return count;
    }
}

class RaceConditionExample{
    public static void main(String[] args) {
        Counter counter = new Counter();

        // Two Threads modifying the shared resource
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



        System.out.println("Final count: "+counter.getCount()); // will always print 2000


    }
}
