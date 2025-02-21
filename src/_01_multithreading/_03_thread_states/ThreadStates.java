package _01_multithreading._03_thread_states;

public class ThreadStates{

    public static void main(String[] args) {

        Thread thread = new Thread(() -> {
            try {

                Thread.sleep(2000);

            }catch (InterruptedException ie){
                ie.printStackTrace();
            }
        });


        System.out.println("State before start: " + thread.getState() + " : "+ thread.getName()); // NEW
        thread.start();
        System.out.println("State after start: " + thread.getState()+ " : "+ thread.getName()); // RUNNABLE or BLOCKED

        try {
            Thread.sleep(500); // Allow some time for the thread to start
            System.out.println("State while running: " + thread.getState()+ ": "+ thread.getName()); // TIMED_WAITING
            thread.join(); // Wait for thread to complete
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("State after completion: " + thread.getState()+ " "+ thread.getName()); // TERMINATED
    }
}
