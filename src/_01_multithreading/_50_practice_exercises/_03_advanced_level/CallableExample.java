package _01_multithreading._50_practice_exercises._03_advanced_level;


import java.util.concurrent.*;

/*
 *
 * Use a Callable instead of Runnable to return a result from a thread.
 *
 * How This Works
 *   ✅ Callable<T> returns a result, unlike Runnable, which does not.
 *   ✅ Future<T> holds the result and allows retrieving it later.
 *   ✅ Using ExecutorService manages thread execution efficiently.
 *   ✅ Main thread waits for all tasks (future.get()) and aggregates results.
 */
class SumTask implements Callable<Integer>{

    private final int start;
    private final int end;

    public SumTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = start; i <= end ; i++) {
            sum += i;
        }
        System.out.println("Sum from "+ start+ " to "+ end + " = "+sum);
        return sum;
    }
}
public class CallableExample{

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        Future<Integer> future1 = executor.submit(new SumTask(1,50));
        Future<Integer> future2 = executor.submit(new SumTask(51,100));
        Future<Integer> future3 = executor.submit(new SumTask(101,150));

        int totalSum = future1.get() + future2.get() + future3.get();

        System.out.println("Total sum = "+totalSum);

        executor.shutdown();

    }
}










