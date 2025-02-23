package _01_multithreading._19_forkjoin_mini_project;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

class FibonacciTask extends RecursiveTask<Long> {

    private final int number;

    public FibonacciTask(int number) {
        this.number = number;
    }
    @Override
    protected Long compute() {
        // Base case
        if (number <= 1 ) return 1L;

        FibonacciTask f1 = new FibonacciTask(number - 1);
        FibonacciTask f2 = new FibonacciTask(number - 2);

        f1.fork();
        Long result2 = f2.compute();
        Long result1 = f1.join();

        return result1 + result2;
    }
}
public class ParallelFibonacci {
    public static void main(String[] args) {

        ForkJoinPool pool = new ForkJoinPool();

        int nthNumber = 4;

        FibonacciTask fibonacciTask = new FibonacciTask(nthNumber);

        Long result = pool.invoke(fibonacciTask);

        System.out.println("Fibonacci of " + nthNumber + " is: " + result);

        pool.shutdown();
    }
}
