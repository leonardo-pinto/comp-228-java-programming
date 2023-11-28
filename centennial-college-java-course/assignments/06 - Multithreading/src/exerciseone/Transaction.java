package exerciseone;

public class Transaction implements Runnable {
    private final Account account;

    public Transaction(Account account) {
        this.account = account;
    }

    @Override
    public void run() {
        this.account.deposit(100);
        this.account.withdraw(200);
    }
}
