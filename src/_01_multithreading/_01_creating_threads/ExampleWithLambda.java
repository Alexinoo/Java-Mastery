package _01_multithreading._01_creating_threads;

public class ExampleWithLambda {
    public static void main(String[] args) {
        Thread thread = new Thread(()-> System.out.println("Running from Lambda..."));
        thread.start();
    }
}
