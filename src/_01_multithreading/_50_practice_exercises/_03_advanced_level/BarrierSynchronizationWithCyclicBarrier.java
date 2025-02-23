package _01_multithreading._50_practice_exercises._03_advanced_level;


import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;


/*
 * Implement a barrier synchronization using CyclicBarrier.
 *  - Ensures multiple threads synchronize at a common point before continuing.
*/
class WorkerTask extends Thread{

    private final CyclicBarrier barrier;

    public WorkerTask(CyclicBarrier barrier,String name) {
        super(name);
        this.barrier = barrier;
    }

    @Override
    public void run() {
        try{
            System.out.println(Thread.currentThread().getName() + " is working...");
            Thread.sleep(1000);

            System.out.println(Thread.currentThread().getName() + " reached the barrier.");
            barrier.await();
            System.out.println(Thread.currentThread().getName() + " continues after barrier.");

        }catch (InterruptedException | BrokenBarrierException e){
            e.printStackTrace();
        }
    }
}
public class BarrierSynchronizationWithCyclicBarrier {

    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(3, ()-> System.out.println("All tasks reached the barrier"));

        for (int i = 1; i <= 3; i++) {
            new WorkerTask(barrier,"Worker-"+i).start();
        }
    }
}
