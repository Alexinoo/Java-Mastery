package _01_multithreading._15_concurrent_collections._05_concurrent_set_implementations;

import java.util.concurrent.ConcurrentSkipListSet;

/*
 * Real-World Use Case of ConcurrentSkipListSet
 *
 *
 * A great use case for ConcurrentSkipListSet is implementing a real-time user session tracking system where multiple threads add and
    remove active users while maintaining a sorted order.

* Scenario: Tracking Active Users in a System
* In a web application, users log in and log out at different times. The system needs to:

* Keep track of active users.
* Maintain the list in a sorted manner (e.g., usernames sorted alphabetically).
* Support concurrent modifications safely.
* Why ConcurrentSkipListSet?
* Thread-Safe: Multiple threads can add/remove users.
* Sorted Order: Users are stored in ascending order automatically.
* Efficient Operations: Provides fast access, insertion, and removal.
*
*
 */
public class ConcurrentSkipListSetExample {

    // Thread-safe, sorted storage of active users
    private final ConcurrentSkipListSet<String> activeUsers = new ConcurrentSkipListSet<>();

    // Method to add a user
    public void userLoggedIn(String username) {
        activeUsers.add(username);
        System.out.println(username + " logged in.");
    }

    // Method to remove a user
    public void userLoggedOut(String username) {
        activeUsers.remove(username);
        System.out.println(username + " logged out.");
    }

    // Display the current active users
    public void displayActiveUsers() {
        System.out.println("\nActive Users:");
        for (String user : activeUsers) {
            System.out.println(user);
        }
    }


    public static void main(String[] args) {

        ConcurrentSkipListSetExample tracker = new ConcurrentSkipListSetExample();

        // Simulating multiple users logging in and out
        Runnable userActivity = ()->{
            tracker.userLoggedIn("Alice");
            tracker.userLoggedIn("Bob");
            tracker.userLoggedIn("Charlie");
            tracker.userLoggedOut("Alice");
            tracker.userLoggedIn("Dave");
        };

        Thread t1 = new Thread(userActivity);
        Thread t2 = new Thread(userActivity);


        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Displaying active users
        tracker.displayActiveUsers();



    }
}
