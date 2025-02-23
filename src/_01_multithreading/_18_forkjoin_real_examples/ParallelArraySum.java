package _01_multithreading._18_forkjoin_real_examples;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/* Parallel Array Summation
 * -------------------------
 *
 * Imagine you need to sum a large array of numbers.
 *
 * Instead of using a single thread, we can use ForkJoinPool to divide the task and compute the sum in parallel.
 *
 */
class ArraySumTask extends RecursiveTask<Long>{
    private final int[] arr;
    private final int start,end;

    private final int THRESHOLD = 3;

    public ArraySumTask(int[] arr, int start, int end) {
        this.arr = arr;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        if (end - start <= THRESHOLD){
            long sum = 0;
            for (int i = start; i < end ; i++) sum += arr[i];
            return sum;

        }else{
            int mid = (start + end) / 2;
            var leftTask = new ArraySumTask(arr , start, mid);
            var rightTask = new ArraySumTask(arr , mid, end);

            leftTask.fork(); // start left task asynchronously
            Long rightResult = rightTask.compute(); // compute right side
            Long leftResult = leftTask.join(); // Get left result

            return rightResult + leftResult;
        }
    }
}
public class ParallelArraySum {
    public static void main(String[] args) {

        ForkJoinPool pool = new ForkJoinPool();

        int[] myIntArray = { 1,2,3,4,5,8,-345};

        Long result = pool.invoke( new ArraySumTask(myIntArray , 0 , myIntArray.length));

        System.out.println("Total Sum of the array "+ Arrays.toString(myIntArray) + " is "+result);
    }
}
