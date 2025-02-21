package _01_multithreading._14_thread_pool_examples;

import java.util.concurrent.*;

/*
 * Web Scraper to Fetch Data from Multiple URLs
 *
 * A web scraper needs to download data from multiple URLs concurrently.
 *
 * Implementation Using CachedThreadPool (dynamically generated)
 *
 *
 * Explanation
 * We use Executors.newCachedThreadPool() which dynamically creates new threads if needed and reuses idle threads.
 * Each URL fetching task is submitted to ExecutorService using Future, which allows retrieving the result once it's ready.
 */

public class WebScraperTask implements Callable<String> {
    private final String url;

    public WebScraperTask(String url) {
        this.url = url;
    }

    @Override
    public String call() {

        System.out.println("Fetching data from " + url + " by "+ Thread.currentThread().getName());

        // simulate network delay
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return "Data from "+ url;
    }
}

class WebScrapper {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();

        String[] urls = {
                "https://example.com/page1",
                "https://example.com/page2",
                "https://example.com/page3"
        };

        for (String url : urls){
            Future<String> results = executor.submit(new WebScraperTask(url));
            System.out.println(results.get());
        }

        executor.shutdown();
    }
}
