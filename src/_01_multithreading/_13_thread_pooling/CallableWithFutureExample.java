package _01_multithreading._13_thread_pooling;

import java.util.concurrent.*;

public class CallableWithFutureExample  {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Callable<String> task = () -> {
            Thread.sleep(2000);
            return "Task Completed";
        };

        Future<String> future = executorService.submit(task);

        System.out.println("Doing other work...");

        String result = future.get(); // Blocks until result is available
        System.out.println("Result: "+ result);

        System.out.println("This code will be blocked and will only run after callable task has completed");

        executorService.shutdown();
    }
}
