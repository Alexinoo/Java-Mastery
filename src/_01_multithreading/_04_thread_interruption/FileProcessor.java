package _01_multithreading._04_thread_interruption;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileProcessor implements Runnable{
    private final String filePath;

    public FileProcessor(String filePath) {
        this.filePath = filePath;
    }


    @Override
    public void run() {
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            String line;
            while( (line = reader.readLine()) != null){

                // check if the thread is interrupted
                if (Thread.currentThread().isInterrupted()){
                    System.out.println("File processing interrupted. Cleaning up...");
                    return; // Exit Gracefully
                }

                // Simulate processing
                System.out.println("Processing: " + line);
                Thread.sleep(500); // Simulating a time-consuming task
            }

        }catch (IOException | InterruptedException e){
            System.out.println("File Processing was stopped: "+ e.getMessage());
        }
    }
}

class ThreadInterruptExample {
    public static void main(String[] args) throws InterruptedException {
        Thread workerThread = new Thread(new FileProcessor("lorem-ipsum.txt"));
        workerThread.start();


        // simulate user cancelling after 3 seconds
        Thread.sleep(3000);
        System.out.println("User requested cancellation. Interrupting the worker thread...");
        workerThread.interrupt();
    }
}
