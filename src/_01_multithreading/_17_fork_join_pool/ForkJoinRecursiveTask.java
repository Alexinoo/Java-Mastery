package _01_multithreading._17_fork_join_pool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/*
 * Using RecursiveTask for Parallel Computation
 *
 * A RecursiveTask<V> is used when a task needs to return a result after breaking into subtasks.
 *
 * A RecursiveTask<Integer> is created to sum numbers from 1 to 10.
 * The task is executed using invoke(), and the result is printed.
 */

class SimpleSumTask extends RecursiveTask<Integer>{
    private final int start, end;

    public SimpleSumTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        for (int i = start; i <= end ; i++) {
            sum += i;
        }
        return sum;
    }
}
public class ForkJoinRecursiveTask {

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();

        int result = pool.invoke(new SimpleSumTask(1,10));
        System.out.println("\n--Sum from 1 to 10: "+result);

        pool.shutdown();
    }
}
