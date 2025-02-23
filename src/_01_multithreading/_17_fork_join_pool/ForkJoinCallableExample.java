package _01_multithreading._17_fork_join_pool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/*
 * Using invoke() for a Callable Task
 *  - If the task needs to return a result, we can use invoke().
 */
public class ForkJoinCallableExample extends RecursiveTask<Integer> {
    private final int[] myIntArray;

    public ForkJoinCallableExample(int[] arr) {
        this.myIntArray = arr;
    }

    @Override
    protected Integer compute() {
        System.out.println(Thread.currentThread().getName()+ " Computing sum of the array");
        int sum = 0;
        for (int number : myIntArray){
            sum += number;
        }
        return sum;
    }


    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();

        int result = pool.invoke(new ForkJoinCallableExample( new int[] {2,4,6,8,10}));

        System.out.println("Sum = "+result);

        pool.shutdown();
    }


}
