package _01_multithreading._17_fork_join_pool;

import java.util.concurrent.ForkJoinPool;

/*
 * 1. Submitting a Simple Task
 *
 *
 * A basic example where we use ForkJoinPool to execute a task that does not return a result.
 *
 */
public class ForkJoinSimpleExample {

    public static void main(String[] args) throws InterruptedException {

        // Create a ForkJoinPool with default parallelism
        ForkJoinPool pool = new ForkJoinPool();

        // Submit a simple Runnable task
        pool.submit(()->{
            System.out.println("Task executed by thread: " + Thread.currentThread().getName());
        });

        Thread.sleep(1000);

        // Shutdown the pool
        pool.shutdown();

       }
}
