package _01_multithreading._21_semaphore_use_case;

import java.util.concurrent.Semaphore;

/*
 * Scenario: An airport has 1 runway, and 5 flights want to take off. Each takeoff takes 3 seconds.
 */

class Airport {

    private final Semaphore runway = new Semaphore(1);

    public void takeOff(String flight){
        try{
            System.out.println(flight+ " is waiting for the runway...");
            runway.acquire(); // Acquire the runway

            System.out.println(flight+ " is taking off...");
            Thread.sleep(3000); // Simulating takeoff

            System.out.println(flight +" has taken off");
            runway.release();

        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
public class AirportSimulation {

    public static void main(String[] args) {

        Airport airport = new Airport();

        Runnable task = ()->{
            String flight = Thread.currentThread().getName();
            airport.takeOff(flight);
        };

        // Create 5 flights trying to take off
        for (int i = 1; i <= 5 ; i++) {
            new Thread(task,"Flight-"+ (100 + i)).start();
        }

    }
}
