package _01_multithreading._21_semaphore_use_case;

import java.util.concurrent.Semaphore;

class PrintShop {

    private final Semaphore sharedPrinter = new Semaphore(2);

    public void print(String printer){
        try {

            System.out.println(printer + " is waiting for a printer...");
            sharedPrinter.acquire(); // acquire a printer


            System.out.println(printer + " is printing...");

            Thread.sleep(3000); // Simulating printing time

            System.out.println(printer + " has finished and released the printer..");
            sharedPrinter.release();  // Release the printer

        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
public class SharedPrinter {

    public static void main(String[] args) {

        PrintShop printShop = new PrintShop();

        Runnable printJobs = ()-> {
            String user = Thread.currentThread().getName();
            printShop.print(user);
        };

        // Creating 5 users to print
        for (int i = 1; i <= 5; i++) {
            new Thread(printJobs,"User-"+i).start();
        }
    }
}
