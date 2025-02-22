package _01_multithreading._50_practice_exercises._02_intermediate_level;

/*
 * Use synchronized to prevent race conditions in a shared counter.
 */
public class UseSynchronizedInSharedCounter {

    private int count;

    public UseSynchronizedInSharedCounter(){
        this.count = 0;
    }

    public void increment(){
        synchronized (this) {
            count++;
        }
    }

    public int getCount() {
        return count;
    }

    public static void main(String[] args) {
        UseSynchronizedInSharedCounter shared = new UseSynchronizedInSharedCounter();

        Thread thread1 = new Thread(()->{
            for (int i = 1; i <= 100 ; i++) {
                shared.increment();
            }
        });

        Thread thread2 = new Thread(()->{
            for (int i = 1; i <= 100 ; i++) {
                shared.increment();
            }
        });

        thread1.start();
        thread2.start();

        try{
            thread1.join();
            thread2.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("Final counter value: "+ shared.getCount());
    }
}
