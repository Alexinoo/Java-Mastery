package _01_multithreading._11_thread_synchronization_usecase;

public class BankAccount{
    private String accountName;
  private double balance;

    public BankAccount(String accountName, double initialBalance) {
        this.accountName = accountName;
        this.balance = initialBalance;
    }

    public synchronized void deposit(double amount){
      if (amount > 0){
          this.balance += amount;
          System.out.println(Thread.currentThread().getName()+ ": deposited "+ amount + " to "+getAccountName()+"'s account. Current balance= "+ getBalance());
      }
  }
    public void withdraw(double amount){
        if (amount > 0 && balance >= amount){
            synchronized (this){
                this.balance -= amount;
            }
            System.out.println(Thread.currentThread().getName()+ ": withdrew "+ amount + " from "+ getAccountName()+ "'s account. Current balance="+ getBalance());
        }else{
            System.out.println(Thread.currentThread().getName()+ " insufficient balance for withdrawal of "+amount);
        }
    }

    public String getAccountName(){
        return this.accountName;
    }

    public double getBalance() {
        return balance;
    }
}

class BankAccountExample {
    public static void main(String[] args) throws InterruptedException{
        BankAccount account = new BankAccount("Alex",1000);

        // Thread for depositing money
        Thread thread1 = new Thread(()->{
            account.deposit(500);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            account.withdraw(200);
        });

        // Another thread for depositing and withdrawing money
        Thread thread2 = new Thread(()->{
            account.deposit(300);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            account.withdraw(150);
        });


        thread1.start();
        thread1.join();

        thread2.start();
        thread2.join();

        System.out.println("Final balance: "+ account.getBalance());
    }
}
