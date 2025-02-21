package _01_multithreading._16_concurrent_collections_use_cases;

import java.time.Instant;
import java.util.concurrent.*;

/* Enhanced ConcurrentSkipListSet Example with Auto-Removal of Inactive Users
 * Now, let's add automatic user session expiration—if a user remains inactive for a certain period
 *      (e.g., 5 seconds), they are automatically removed from the active user list.
 *
 *
 * Key Features of This Enhanced Version
 *
 * Auto-Removes Inactive Users: A background thread removes users if they are inactive for SESSION_TIMEOUT seconds.
 * Tracks Last Activity: Uses ConcurrentHashMap to store timestamps.
 * Thread-Safe & Efficient: Uses ConcurrentSkipListSet for sorted storage and ScheduledExecutorService for periodic cleanup.
 */

public class ActiveUserTracker {
    private final ConcurrentSkipListSet<String> activeUsers  = new ConcurrentSkipListSet();
    private final ConcurrentSkipListMap<String,Instant>lastActiveTime = new ConcurrentSkipListMap<>();

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    public static final long SESSION_TIMEOUT = 5; // Auto-remove users after 5 seconds

    public ActiveUserTracker() {
        scheduler.scheduleAtFixedRate(this::removeInactiveUsers,3,3 , TimeUnit.SECONDS);
    }


    public void userLoggedIn(String username){
        activeUsers.add(username);
        lastActiveTime.put(username , Instant.now());
        System.out.println(username+ " logged in.");

    }

    public void userActivity(String username){
        if (activeUsers.contains(username)){
            lastActiveTime.put(username,Instant.now());
            System.out.println(username + " is active");
        }
    }

    public void userLoggedOut(String username){
        activeUsers.remove(username);
        lastActiveTime.remove(username , Instant.now());
        System.out.println(username+ " logged out.");

    }

    public void removeInactiveUsers(){
        Instant now = Instant.now();
        for (String user : activeUsers){
            Instant lastActive = lastActiveTime.get(user);
            if (lastActive != null && lastActive.plusSeconds(SESSION_TIMEOUT).isBefore(now)){
                userLoggedOut(user);
            }
        }


        // Shutdown scheduler when no active users are left
        if(activeUsers.isEmpty()) {
            System.out.println("\nNo active users left. Shutting down...");
            scheduler.shutdown();
        }
    }


    public void displayActiveUsers(){
        System.out.println("\nActive Users");
        for (String user : activeUsers){
            System.out.println(user);
        }
    }


    public static void main(String[] args) throws InterruptedException {
        ActiveUserTracker tracker = new ActiveUserTracker();

        tracker.userLoggedIn("Alice");
        tracker.userLoggedIn("Bob");
        tracker.userLoggedIn("Charlie");

        Thread.sleep(3000);
        tracker.userActivity("Alice"); // Refresh Alice's session
        Thread.sleep(3000);


        tracker.displayActiveUsers();   // Bob and Charlie should be removed


        Thread.sleep(5000);
        tracker.displayActiveUsers();   // Alice should also be removed


        // Wait for the scheduler to shut down before exiting
        tracker.scheduler.awaitTermination(5, TimeUnit.SECONDS);


    }
}
