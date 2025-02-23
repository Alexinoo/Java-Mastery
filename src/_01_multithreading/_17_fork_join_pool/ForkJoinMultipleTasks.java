package _01_multithreading._17_fork_join_pool;

import java.util.concurrent.ForkJoinPool;

/* Running Multiple Tasks in Parallel
 *
 *
 * We can execute multiple tasks simultaneously.
 * Multiple tasks are executed in parallel using the ForkJoinPool.commonPool().
 */
public class ForkJoinMultipleTasks {

    public static void main(String[] args) throws InterruptedException {

        ForkJoinPool pool = new ForkJoinPool();

        // Tasks to run
        Runnable task1 = () -> System.out.println("Task 1 executed by " + Thread.currentThread().getName());
        Runnable task2 = () -> System.out.println("Task 2 executed by " + Thread.currentThread().getName());

        // RUn multiple parallel tasks
        pool.submit(()->{
            ForkJoinPool.commonPool().execute(task1);
            ForkJoinPool.commonPool().execute(task2);
        });

        Thread.sleep(1000);

        pool.shutdown();
    }
}
