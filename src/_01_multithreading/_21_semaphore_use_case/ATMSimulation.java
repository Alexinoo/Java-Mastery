package _01_multithreading._21_semaphore_use_case;

import java.util.concurrent.Semaphore;

/*
 * Scenario: A bank has 2 ATM machines, but 7 people want to withdraw cash. Each withdrawal takes 5 seconds.
 */
class ATM {
    private final Semaphore atms = new Semaphore(2);

    public void withdraw(String person){
        try {
            System.out.println(person + " is waiting for an ATM...");
            atms.acquire(); // Acquire an ATM

            System.out.println(person + " is using an ATM...");
            Thread.sleep(5000); // simulate withdrawal time

            System.out.println(person + " finished and left the ATM");
            atms.release(); // Release the ATM
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
public class ATMSimulation {
    public static void main(String[] args) {

        ATM atm = new ATM();

        Runnable task = ()-> {
            String person = Thread.currentThread().getName();
            atm.withdraw(person);
        };

        // Creating 7 people trying to use the ATM
        for (int i = 1; i <= 7; i++) {
            new Thread(task,"Person-"+i).start();
        }

    }
}
