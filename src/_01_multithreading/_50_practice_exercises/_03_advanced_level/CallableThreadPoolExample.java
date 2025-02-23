package _01_multithreading._50_practice_exercises._03_advanced_level;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/*
 * Execute multiple Callable tasks in a thread pool and retrieve results using Future.
 *
 *
 * How This Works
 *  ✅ Callable<Long> returns the factorial result after computation.
 *  ✅ We use ExecutorService with a fixed thread pool to execute tasks in parallel.
 *  ✅ Each task runs independently, and results are stored in Future<Long>.
 *  ✅ Main thread waits for all tasks (future.get()) before printing results.
 */

class FactorialTask implements Callable<Long> {

    private final int number;

    public FactorialTask(int number) {
        this.number = number;
    }

    @Override
    public Long call() throws Exception {
        long fact = 1;

        for (int i = 1; i <= number ; i++) {
            fact *= i;
        }
        return fact;
    }
}
public class CallableThreadPoolExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executor = Executors.newFixedThreadPool(3);

        List<Future<Long>> futures = new ArrayList<>();

        int[] numbers = {5, 7, 10, 12, 15};

        // submit tasks
        for (int number : numbers){
           Future<Long> future = executor.submit(new FactorialTask(number));
            futures.add(future);
        }


        // Retrieve and print results
        for (Future<Long> future : futures){
            System.out.println("Computed Result: "+ future.get());
        }

        executor.shutdown();
    }
}
