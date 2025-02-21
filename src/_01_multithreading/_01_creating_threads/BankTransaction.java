package _01_multithreading._01_creating_threads;

public class BankTransaction implements Runnable{
    private String transactionName;

    public BankTransaction(String transactionName) {
        this.transactionName = transactionName;
    }

    @Override
    public void run() {
        System.out.println(transactionName + " started by "+Thread.currentThread().getName());

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(transactionName + " completed by "+Thread.currentThread().getName());


    }
}
class BankSystem{
    public static void main(String[] args) {
        Thread deposit = new Thread(new BankTransaction("Deposit"));
        Thread withdrawal = new Thread(new BankTransaction("Withdraw"));

        deposit.start();

        try{
            deposit.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        withdrawal.start();

    }
}












