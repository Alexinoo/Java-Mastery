package _01_multithreading._13_thread_pooling;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorExample {

    public static void main(String[] args) throws InterruptedException {

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

        Runnable scheduledToRunAfter3Seconds = ()-> System.out.println("scheduled To Run After 3 Seconds "+System.currentTimeMillis());
        Runnable scheduledToRunEvery5SecondsAfterInitialDelay = ()-> System.out.println("scheduled To Run Every 5 Seconds After Initial Delay "+System.currentTimeMillis());
        Runnable scheduledToRunWithAFixedDelayAfterCompletion = ()-> System.out.println("scheduled To Run With a Fixed Delay After 5 Seconds "+System.currentTimeMillis());

        scheduler.schedule(scheduledToRunAfter3Seconds,3 , TimeUnit.SECONDS); // Runs after 3 seconds

        scheduler.scheduleAtFixedRate(scheduledToRunEvery5SecondsAfterInitialDelay,1,5,TimeUnit.SECONDS); // Runs every 5 seconds after an initial delay of 1 sec

        scheduler.scheduleWithFixedDelay(scheduledToRunWithAFixedDelayAfterCompletion,1,5,TimeUnit.SECONDS); // Runs with a fixed delay after completion


        Thread.sleep(15000); // Allow time for scheduled tasks to evaluate


        scheduler.shutdown();
    }
}
