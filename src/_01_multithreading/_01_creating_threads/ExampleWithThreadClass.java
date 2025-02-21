package _01_multithreading._01_creating_threads;


public class ExampleWithThreadClass extends Thread{

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
        System.out.println(Thread.currentThread().getName() + " is running task "+i);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }

}

class MainClass {
    public static void main(String[] args) {
        ExampleWithThreadClass thread = new ExampleWithThreadClass();
        thread.start();
    }
}