package _01_multithreading._21_semaphore_use_case;

import java.util.concurrent.Semaphore;

class Restaurant {

    private Semaphore tables = new Semaphore(3); // 3 tables available

    public void dine(String customer){
        try{

            System.out.println(customer + " is waiting for a table...");
            tables.acquire();  // acquire a table

            System.out.println(customer + " got a table and is eating...");

            Thread.sleep(4000); //Simulating eating time

            System.out.println(customer + " finished eating and left the table");
            tables.release(); // Release the table

        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
public class RestaurantSimulation {

    public static void main(String[] args) {

        Restaurant restaurant = new Restaurant();

        Runnable task = ()-> {
            String customer = Thread.currentThread().getName();
            restaurant.dine(customer);
        };

        // Creating 6 customers trying to get a table
        for (int i = 1; i <= 6; i++) {
            new Thread(task,"Customer-"+i).start();
        }
    }
}
