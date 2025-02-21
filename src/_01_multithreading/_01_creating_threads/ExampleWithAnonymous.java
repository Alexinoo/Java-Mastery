package _01_multithreading._01_creating_threads;

public class ExampleWithAnonymous {

    public static void main(String[] args) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Running from the anonymous block...");
            }
        });

        thread.start();
    }
}
