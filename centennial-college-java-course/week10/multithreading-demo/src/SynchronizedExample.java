class DatabaseConnection{
    public synchronized void executeQuery(String query) throws InterruptedException {
        System.out.println("Executing Query: " + query + " by Thread: " + Thread.currentThread().getName());
        Thread.sleep(2000);
        System.out.println("Query Executed: " + query + " by Thread: " + Thread.currentThread().getName());
    }
}

public class SynchronizedExample {
    public static void main(String[] args) {
        final DatabaseConnection databaseConnection = new DatabaseConnection();

        //create 3 queries
        Runnable queryTasks = () -> {
          for (int i=0;i<3;i++){
              try {
                  databaseConnection.executeQuery("select * from products");
              } catch (InterruptedException e) {
                  throw new RuntimeException(e);
              }
          }
        };

        //2 threads
        Thread thread1 = new Thread(queryTasks); // 3 queries
        Thread thread2 = new Thread(queryTasks); // 3 queries

        //start thread execution
        thread1.start();
        thread2.start();
    }
}
