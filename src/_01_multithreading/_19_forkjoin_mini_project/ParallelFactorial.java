package _01_multithreading._19_forkjoin_mini_project;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

class FactorialTask extends RecursiveTask<Long>{
    private final int start,end;
    private static final int THRESHOLD = 3;

    public FactorialTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        if ( (end - start) <= THRESHOLD){
            Long result = 1L;
            for (int i = start; i <= end ; i++) {
                result *= i;
            }
            return result;
        }else{
            int mid = (start + end) / 2;

            FactorialTask leftTask = new FactorialTask(start, mid);
            FactorialTask rightTask = new FactorialTask(mid+1, end);

            leftTask.fork();
            Long rightResult = rightTask.compute();
            Long leftResult = leftTask.join();

            Long result = rightResult * leftResult;
            return result;

        }
    }
}
public class ParallelFactorial {

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();

        int number = 10;

        Long result = pool.invoke(new FactorialTask(1,number));

        System.out.println("Factorial of "+number+ " is: "+result);

        pool.shutdown();
    }
}
