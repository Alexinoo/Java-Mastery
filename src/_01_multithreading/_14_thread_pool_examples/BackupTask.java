package _01_multithreading._14_thread_pool_examples;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/*
 * Scheduled Task for Auto-Backup System
 * A system needs to automatically back up files every 5 seconds.
 *
 * Implementation Using ScheduledExecutorService
 *
 * Explanation
 * We use Executors.newScheduledThreadPool(1) for scheduling recurring tasks.
 * scheduleAtFixedRate() ensures backups run every 5 seconds, starting immediately.
 */
public class BackupTask implements Runnable{
    @Override
    public void run() {
        System.out.println("Performing backup at "+ System.currentTimeMillis());
    }
}

class AutoBackupSystem{

    public static void main(String[] args) throws InterruptedException {

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        scheduler.scheduleAtFixedRate(new BackupTask(),0 , 5, TimeUnit.SECONDS);


        // Give Thread some time to run
        Thread.sleep(15000);

        scheduler.shutdown();
    }
}
