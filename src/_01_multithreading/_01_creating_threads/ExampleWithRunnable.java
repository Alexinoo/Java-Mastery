package _01_multithreading._01_creating_threads;

public class ExampleWithRunnable implements Runnable {
    @Override
    public void run() {
        for (int i = 1; i < 5; i++) {
        System.out.println(Thread.currentThread().getName()+ " Running task "+i);

        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        }
    }
}

class RunnableExample {
    public static void main(String[] args) {
        Thread thread = new Thread(new ExampleWithRunnable());
        thread.start();

    }
}
