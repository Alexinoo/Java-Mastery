package _01_multithreading._50_practice_exercises._02_intermediate_level;

import java.util.concurrent.atomic.AtomicInteger;

public class UseAtomicIntegerToIncrementSharedCounter {

    private AtomicInteger count = new AtomicInteger(0);

    public void increment(){
        count.getAndIncrement();
    }

    public AtomicInteger getCount() {
        return count;
    }

    public static void main(String[] args) throws InterruptedException{
        UseAtomicIntegerToIncrementSharedCounter counter = new UseAtomicIntegerToIncrementSharedCounter();

        Thread thread1 = new Thread(()->{
            for (int i = 1; i <= 1000 ; i++) {
                counter.increment();
            }
        });

        Thread thread2 = new Thread(()->{
            for (int i = 1; i <= 1000 ; i++) {
                counter.increment();
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("Final Counter: "+counter.getCount());
    }
}
