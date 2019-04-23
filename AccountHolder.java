import java.util.Random;

public class AccountHolder extends Thread{

    private final static int MIN_WRITING_TIME = 1000;
    private final static int MAX_WRITING_TIME = 4000;

    private Random random = new Random();


    private BankAccount ba;

    public AccountHolder(String id, BankAccount ba){
        super(id);
        this.ba = ba;
    }

    public void run(){
        try {
            while(true){
                deposit();
                randomWait(MIN_WRITING_TIME,MAX_WRITING_TIME);
                withdraw();
                randomWait(MIN_WRITING_TIME,MAX_WRITING_TIME);
                checkBalance();
                randomWait(MIN_WRITING_TIME,MAX_WRITING_TIME);
            }
        } catch (InterruptedException e) {}
    }

    private void deposit() throws InterruptedException {

        ba.deposit(10);
    }

    private void withdraw() throws InterruptedException {
        ba.withdraw(5);
    }

    private void checkBalance() throws InterruptedException {
        ba.checkBalance();
    }

    private void randomWait(int minTime, int maxTime) throws InterruptedException {
        Thread.sleep((random.nextInt(maxTime - minTime + 1) + minTime));
    }


}
