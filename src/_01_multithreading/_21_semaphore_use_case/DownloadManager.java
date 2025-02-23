package _01_multithreading._21_semaphore_use_case;

import java.util.concurrent.Semaphore;

/*
 * Scenario: A file download manager allows only 3 files to be downloaded at the same time. There are 6 files.
 */
class FileDownloader {
    private final Semaphore slots = new Semaphore(3); // Only 3 files can download simultaneously

    public void downloadFile(String file){

        try {
            System.out.println(file + " is waiting for a download slot...");
            slots.acquire(); // Acquire a download slot

            System.out.println(file + " is downloading...");
            Thread.sleep(5000); // Simulating downloading time

            System.out.println(file + " finished downloading");
            slots.release(); // Release the slot
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
public class DownloadManager {
    public static void main(String[] args) {
        FileDownloader downloader = new FileDownloader();

        Runnable task = ()-> {
            String file = Thread.currentThread().getName();
            downloader.downloadFile(file);
        };

        // Creating 6 file downloads
        for (int i = 1; i <= 6; i++) {
            new Thread(task,"File-"+i).start();
        }
    }
}
