package _01_multithreading._50_practice_exercises._02_intermediate_level;

/*
 * Implement a thread-safe Singleton using double-checked locking
 */
public class ThreadSafeSingleton {

    private static volatile ThreadSafeSingleton instance;

    private ThreadSafeSingleton (){}

    public static ThreadSafeSingleton getInstance(){

        if (instance == null){
            synchronized (ThreadSafeSingleton.class){
                if (instance == null){
                    instance = new ThreadSafeSingleton();
                }
            }
        }

        return instance;
    }



}
