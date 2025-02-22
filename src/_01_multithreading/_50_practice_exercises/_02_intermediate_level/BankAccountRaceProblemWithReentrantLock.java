package _01_multithreading._50_practice_exercises._02_intermediate_level;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * Use ReentrantLock instead of synchronized for thread safety.
 */
public class BankAccountRaceProblemWithReentrantLock {

    private double balance;
    private final Lock lock = new ReentrantLock(true);

    public BankAccountRaceProblemWithReentrantLock(double initialBalance){
        this.balance = initialBalance;
    }

    public void deposit(double amount){
        lock.lock();
        try{
            if (amount > 0){
                balance += amount;
                System.out.println(Thread.currentThread().getName() + " deposited "+amount+ " successfully. New Balance is "+balance);
            }else{
                System.out.println("Amount cannot be less than zero (0)");
            }
        }finally {
            lock.unlock();
        }

    }

    public void withdraw(double amount){
        lock.lock();
        try{
            if (amount > 0 && amount <= balance){
                balance -= amount;
                System.out.println(Thread.currentThread().getName() + " withdrew "+amount+ " successfully. New Balance is "+balance);
            }else{
                System.out.println("Withdrawal of "+ amount + " failed. INSUFFICIENT FUNDS!. Current Balance is $" +balance);
            }
        }finally {
            lock.unlock();
        }
    }

    private double getBalance() {
        return balance;
    }

    public static void main(String[] args) {
        BankAccountRaceProblemWithReentrantLock account = new BankAccountRaceProblemWithReentrantLock(1000);

        Thread thread1 = new Thread(()->{
           account.deposit(1000);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            account.withdraw(200);
        });

        Thread thread2 = new Thread(()->{
            account.deposit(500);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            account.withdraw(300);
        });

        thread1.start();
        thread2.start();
    }
}


















