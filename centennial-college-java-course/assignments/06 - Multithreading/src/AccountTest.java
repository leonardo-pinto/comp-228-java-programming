import exerciseone.Account;
import exerciseone.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AccountTest {
    public static void main(String[] args) {
        Account account = new Account("Leonardo", 10000);


        // This was my understanding from the instructions
        // Use an ArrayList to create a list of three or more Transaction objects.
        // Use method execute of ExecutorService to execute the threads. Display the results.

        // It says to use the execute method.
        // In my opinion it would be better to implement the Callable interface and use
        // the invokeAll method.
        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(new Transaction(account));
        transactionList.add(new Transaction(account));
        transactionList.add(new Transaction(account));

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (Transaction transaction: transactionList) {
            executorService.execute(new Thread(transaction));
        }

        executorService.shutdown();


        // Implementation without executor service
        //        Thread transactionThread1 = new Thread(new Transaction(account));
        //        Thread transactionThread2 = new Thread(new Transaction(account));
        //        Thread transactionThread3 = new Thread(new Transaction(account));
        //
        //        transactionThread1.start();
        //        transactionThread2.start();
        //        transactionThread3.start();
    }
}
