package exerciseone;

public class Account {
    private String holderName;
    private double balance;

    public Account(String holderName, double balance) {
        this.holderName = holderName;
        this.balance = balance;
    }

    public synchronized void deposit(double value) {
        System.out.println("There is a new deposit for " + getHolderName());
        System.out.println("Depositing $" + value);
        balance += value;
    }

    public synchronized void withdraw(double value) {
        System.out.println("There is a new withdraw for " + getHolderName());
        if (balance - value >= 0) {
            System.out.println("Withdrawing $" + value);
            balance -= value;
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "holderName='" + holderName + '\'' +
                ", balance=" + balance +
                '}';
    }
}
