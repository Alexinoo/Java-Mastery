package _01_multithreading._50_practice_exercises._02_intermediate_level;

/*
 * Solve the classic bank account race condition problem.
 */
public class BankAccountRaceCondition {

    private double balance;

    public BankAccountRaceCondition(double initialBalance){
        this.balance = initialBalance;
    }

    public synchronized void deposit(double amount){
        if (amount > 0){
            balance += amount;
            System.out.println(Thread.currentThread().getName()+ " deposited "+ amount + ". Balance is $"+balance);
        }else {
            System.out.println("Amount can not be less than 0");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && balance >= amount){
            synchronized (this) {
                balance -= amount;
            }
            System.out.println(Thread.currentThread().getName()+ " withdrew "+ amount + ". Balance is $"+balance);
        }else{
            System.out.println("Withdrawal of "+ amount+" failed..Insufficient funds!.. Current Balance is $"+balance);

        }
    }

    public double getBalance() {
        return balance;
    }

    public static void main(String[] args) {
        BankAccountRaceCondition account = new BankAccountRaceCondition(1000);

        Thread thread1 = new Thread(()->{
            account.deposit(-50);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            account.withdraw(200);
        });

        Thread thread2 = new Thread(()->{
            account.deposit(300);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            account.withdraw(1600);
        });

        try {
            thread1.start();
            thread1.join();
            thread2.start();
            thread2.join();

        }catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("Final Balance: $"+account.getBalance());

       }
}










