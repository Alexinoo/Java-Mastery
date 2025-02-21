package _01_multithreading._02_callable_future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MyTask implements Callable<String> {
    @Override
    public String call() throws Exception {
        Thread.sleep(2000); // simulate some delay
        return "Callable task executed";
    }
}

class MyTaskExample{
    public static void main(String[] args) throws Exception {

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> future = executorService.submit(new MyTask());

        String result = future.get();
        System.out.println(result);

        executorService.shutdown();
    }
}
