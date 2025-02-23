package _01_multithreading._14_thread_pool_examples;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

class FileDownloader implements Runnable {
    private final String fileUrl;

    private final CountDownLatch latch;

    private static int completedDownloads = 0;

    private final Object lock = new Object();

    public FileDownloader(String fileUrl, CountDownLatch latch) {
        this.fileUrl = fileUrl;
        this.latch = latch;
    }

    @Override
    public void run() {
        try{
            System.out.println(Thread.currentThread().getName() + " downloading.." +fileUrl);

            // Simulate some download time
            Thread.sleep(ThreadLocalRandom.current().nextInt(1000,10000));

            // Increase the no of files downloaded - synchronize - only 1 thread can update at a time
            synchronized (lock) {
                completedDownloads++;
                System.out.println("Download complete: "+ fileUrl + " ("+ completedDownloads +" files downloaded)");
            }
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }finally {
            latch.countDown();
        }
    }
}
public class MultithreadedDownloader {
    public static void main(String[] args) {
        List<String> fileUrls = Arrays.asList("file1.zip","file2.zip","file3.zip","file4.zip","file5.zip");
        CountDownLatch latch = new CountDownLatch(fileUrls.size());

        ExecutorService executor = Executors.newFixedThreadPool(3);

        for (String url : fileUrls){
            executor.execute(new FileDownloader(url,latch));
        }

        try {
            latch.await();  // Wait until all downloads are complete
            System.out.println("All downloads complete");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            executor.shutdown();
        }


    }
}
