package _01_multithreading._12_inter_thread_communication;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
    private String accountName;
    private double balance;

    private final static Lock lock = new ReentrantLock();
    private final static Condition sufficentFunds  = lock.newCondition();
    public void deposit(double amount){
        lock.lock();
        try {
            if (amount > 0){
                this.balance += amount;
                System.out.println(Thread.currentThread().getName()+ " deposited: $"+ amount + " | Balance: $" +getBalance());
                sufficentFunds.signalAll();     // Notify waiting threads that money is available
            }
        }finally {
            lock.unlock();
        }
    }

    public void withdraw(double amount){
        lock.lock();
        try{
            while( balance < amount){
                System.out.println(Thread.currentThread().getName() + " is waiting to withdraw: $" + amount);
                sufficentFunds.await();     // wait until enough funds are available
            }
            balance -= amount;
            System.out.println(Thread.currentThread().getName() + " withdrew: $" + amount + " | Balance: $" + balance);

        }catch(InterruptedException e){


        }finally {
            lock.unlock();
        }
    }

    public String getAccountName() {
        return accountName;
    }

    public double getBalance() {
        return balance;
    }

    public static void main(String[] args) {

        BankAccount account = new BankAccount();


        /* Single Deposit

        // Withdrawal thread (will wait if balance is insufficient)
        Thread withdrawalThread = new Thread(()->{
            account.withdraw(100);
        },"WithdrawalThread");


        //Deposit thread (will add money after some delay)
        Thread depositThread = new Thread(()->{
            try {
                Thread.sleep(2000);
                account.deposit(150);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        },"DepositThread");

        withdrawalThread.start();
        depositThread.start();


        */


        // Creating multiple withdrawer threads
        Thread withdrawer1 = new Thread(() -> account.withdraw(100), "Withdrawer-1");
        Thread withdrawer2 = new Thread(() -> account.withdraw(50), "Withdrawer-2");
        Thread withdrawer3 = new Thread(() -> account.withdraw(80), "Withdrawer-3");


        // Creating depositor threads
        Thread depositor1 = new Thread(() -> {
            try {
                Thread.sleep(2000); // Simulating delay before deposit
                account.deposit(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Depositor-1");

        Thread depositor2 = new Thread(() -> {
            try {
                Thread.sleep(4000); // Simulating another deposit after some time
                account.deposit(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Depositor-2");




        // start all threads
        // Start all threads
        withdrawer1.start();
        withdrawer2.start();
        withdrawer3.start();
        depositor1.start();
        depositor2.start();
    }

}
