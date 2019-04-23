public class Bank438 {

    public static void main(String[] args) {

        BankAccount bankAccount1 = new BankAccount();
        BankAccount bankAccount2 = new BankAccount();
        BankAccount bankAccount3 = new BankAccount();

        int holder1 = 3;
        int holder2 = 2;
        int holder3 = 1;

        AccountHolder[] accounts1 = new AccountHolder[holder1];
        AccountHolder[] accounts2 = new AccountHolder[holder2];
        AccountHolder[] accounts3 = new AccountHolder[holder3];

        for (int i = 0; i < holder1; i++){
            String name = "Bank Account 1 Account Holder " + Integer.toString(i);
            accounts1[i] = new AccountHolder(name,bankAccount1);
            accounts1[i].start();
        }

        for (int i = 0; i < holder2; i++){
            String name = "Bank Account 2 Account Holder " + Integer.toString(i);
            accounts2[i] = new AccountHolder(name,bankAccount2);
            accounts2[i].start();
        }

        for (int i = 0; i < holder3; i++){
            String name = "Bank Account 3 Account Holder " + Integer.toString(i);
            accounts3[i] = new AccountHolder(name,bankAccount3);
            accounts3[i].start();
        }


    }
}
