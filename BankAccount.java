import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {

    private int numOfReaders = 0;
    private boolean writerIsWriting = false;

    private Lock l = new ReentrantLock();
    private Condition canWrite = l.newCondition();
    private Condition canRead = l.newCondition();

    private int balance = 10000;

    public BankAccount(){
        this.balance = balance;
    }

    public void deposit(int amount){
        l.lock();
        try {
           while (numOfReaders > 0 || writerIsWriting){
               canWrite.await();
           }
           writerIsWriting = true;
            System.out.println(Thread.currentThread().getName() + " begins to deposit");
            balance += amount;
            System.out.println(Thread.currentThread().getName() + " ends deposit");
           writerIsWriting = false;
           canRead.signalAll();
           canWrite.signal();
        } catch (Exception e) { }
        finally {
            l.unlock();
        }
    }

    public void withdraw(int amount){
        l.lock();
        try {
            while (numOfReaders > 0 || writerIsWriting){
                canWrite.await();
            }
            writerIsWriting = true;
            System.out.println(Thread.currentThread().getName() + " begins to withdraw");
            balance -= amount;
            System.out.println(Thread.currentThread().getName() + " ends withdrawal");
            writerIsWriting = false;
            canRead.signalAll();
            canWrite.signal();
        } catch (Exception e) { }
        finally {
            l.unlock();
        }
    }

    public void checkBalance() throws InterruptedException{
        l.lock();
        try {
            while (writerIsWriting){
                canRead.await();
            }
            numOfReaders++;
            System.out.println(Thread.currentThread().getName() + " checks the balance: " + balance);
            numOfReaders--;
            if (numOfReaders == 0) {
                canWrite.signal();
            }
        } finally {
            l.unlock();
        }
    }
}
