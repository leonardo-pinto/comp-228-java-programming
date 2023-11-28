import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//number:1 -> 100 to 199
//number:2 -> 200 to 299
//number:3 -> 300 to 399
class Task extends Thread{
    private int number;

    public Task(int number){
        this.number = number;
    }

    //signature method
    public void run(){
        System.out.print("\nTask " + number + " started");
        for (int i=number * 100; i<number * 100 + 99;i++){
            System.out.print(i + " ");
        }
        System.out.print("\nTask " + number + " done");
    }
}

public class ExecutorServiceExample {
    public static void main(String[] args) {
        //run single thread
        //ExecutorService executorService = Executors.newSingleThreadExecutor();

        //run multiple thread
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.execute(new Task(1));
        executorService.execute(new Task(2));
        executorService.execute(new Task(3));
        executorService.execute(new Task(4));
        //shutdown executor service
        executorService.shutdown();
    }
}

