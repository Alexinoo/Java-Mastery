package _01_multithreading._50_practice_exercises._03_advanced_level;

import java.util.concurrent.Semaphore;

/*
 * Simulate a parking lot system using Semaphore.
 *  -  Ensures only a limited number of threads (cars) access a resource at a time.
 */
class ParkingLotSystem {

    private final Semaphore slots;

    public ParkingLotSystem(int capacity) {
        this.slots = new Semaphore(capacity);
    }

    public void park(String car){
        try{
            System.out.println(car +" is waiting for a slot...");
            slots.acquire();
            System.out.println(car + " has parked.");
            Thread.sleep(2000);
            slots.release();
            System.out.println(car + " has left.");
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}

class SimulateParkingLotSystemUsingSemaphore {

    public static void main(String[] args) {
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem(2);

        for (int i = 1; i <= 5 ; i++) {
            final String car = "Car-"+i;
            new Thread(()-> parkingLotSystem.park(car) ).start();
        }
    }
}
