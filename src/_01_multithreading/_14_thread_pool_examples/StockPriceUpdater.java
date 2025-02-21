package _01_multithreading._14_thread_pool_examples;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* Stock Price Updater (Real-Time Task)
 *
 * A system updates stock prices every 3 seconds.
 *
 * Implementation Using ScheduledExecutorService
 *
 * Explanation
 * Simulates stock price updates every 3 seconds using scheduleAtFixedRate().
 * Ideal for real-time financial applications.
 */

public class StockPriceUpdater implements Runnable {

    private final String stockSymbol;

    public StockPriceUpdater(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    @Override
    public void run() {
        double price = 100 + new Random().nextDouble() * 10;
        System.out.println("Updated price of "+ stockSymbol + ": $" +price);
    }
}


class StockMarketUpdater {
    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        scheduler.scheduleAtFixedRate(new StockPriceUpdater("AAPL"),0,3, TimeUnit.SECONDS);


        Thread.sleep(15000);

        scheduler.shutdown();
    }
}
