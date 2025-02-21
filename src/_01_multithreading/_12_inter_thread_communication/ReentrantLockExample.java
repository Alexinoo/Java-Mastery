package _01_multithreading._12_inter_thread_communication;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample {

    private static final Lock lock = new ReentrantLock();

    public static void main(String[] args) {

        Thread t1 = new Thread(()-> criticalSection("Thread-1"));
        Thread t2 = new Thread(()-> criticalSection("Thread-2"));

        t1.start();
        t2.start();

    }

    private static void criticalSection(String threadName){
        lock.lock();                    // Acquiring the lock

        try {
            System.out.println(threadName + " has acquired the lock");
            Thread.sleep(2000);   // simulate some task performed by current thread in critical section
            System.out.println(threadName + " is inside the critical section");
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            System.out.println(threadName + " has released the lock");
            lock.unlock();
        }
    }
}
