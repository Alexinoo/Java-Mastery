package _01_multithreading._21_semaphore_use_case;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

class ParkingLot {
    private Semaphore parkingSlots = new Semaphore(3);

    public void parkCar(String carName){
        try{
            System.out.println(carName + " is trying to park...");
            if (parkingSlots.tryAcquire(3, TimeUnit.SECONDS)) { // Try acquiring a slot within 3 seconds
                System.out.println(carName + " has parked.");
                Thread.sleep(3000); // simulate parking duration

                System.out.println(carName+ " is leaving the parking lot");
                parkingSlots.release(); // Free up the slot
            }else{
                System.out.println(carName +" couldn't find a parking slot and left");
            }

        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
public class ParkingLotSimulation {
    public static void main(String[] args) {

        ParkingLot lot = new ParkingLot();

        Runnable car = ()-> {
            String carName = Thread.currentThread().getName();
            lot.parkCar(carName);
        };


        // 6 cars trying to park, but only 3 slots
        for (int i = 1; i <= 6 ; i++) {
            new Thread(car,"Car-"+i).start();
        }
    }
}
