package _01_multithreading._14_thread_pool_examples;

/*
 * Parallel Processing of Large Data Files
 *
 * A system needs to process multiple large files in parallel.
 *
 *
 * Implementation Using Work-Stealing Pool
 *
 *
 * Explanation
 * Executors.newWorkStealingPool() dynamically assigns tasks to available threads.
 * Useful for CPU-intensive workloads as it maximizes CPU utilization.
 *
 */

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FileProcessorTask implements Runnable{

    private final String fileName;

    public FileProcessorTask(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void run() {
        System.out.println("Processing file: "+ fileName + " by "+ Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Completed processing file: "+fileName);
    }
}

class ParallelFileProcessing {
    public static void main(String[] args) throws InterruptedException {

        ExecutorService executor = Executors.newWorkStealingPool();

        List<String> files = List.of("file1.txt","file2.txt","file3.txt","file4.txt");

        for (String file : files){
            executor.submit(new FileProcessorTask(file));
        }

        Thread.sleep(5000);

        executor.shutdown();
    }
}
