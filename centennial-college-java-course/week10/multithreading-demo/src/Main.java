//Threads can be implemented by using two ways:
//1. Extending the Thread class - task 1
class Task1 extends Thread{
    //signature method of Thread class - code inside this method
    public void run(){
        System.out.print("\ntask 1 started");
        for (int i=100;i<=199;i++){
            System.out.print(i + " ");
        }
        Thread.yield();
        System.out.print("\ntask 1 done");
    }
}

//2. Implementing the Runnable interface - task 2
class Task2 implements Runnable{
    @Override
    public void run() {
        System.out.print("\ntask 2 started");
        for (int i=200;i<=299;i++){
            System.out.print(i + " ");
        }
        System.out.print("\ntask 2 done");
    }
}

//    Lifecycle or states of Thread:
//        1. New
//        2. Runnable
//        3. Running
//        4. Blocked/Waiting - MySQL Server - it depends on external service (database)
//        5. Terminated/Dead - task is over

//Main class - psvm
public class Main {
    public static void main(String[] args) throws InterruptedException {
        //task 1 - extending Thread class
        System.out.print("\ntask 1 kicked off");
        //create object of Thread class
        Task1 task1 = new Task1();

        //set priority
        //task1.setPriority(1);

        //start task
        task1.start();

        //task 2 - implementing Runnable Interface
        System.out.print("\ntask 2 kicked off");
        //create of class in which Runnable interface is implemented
        Task2 task2 = new Task2();
        //create object of Thread class
        Thread task2Thread = new Thread(task2);

        //set priority
        //task2Thread.setPriority(10);

        //start task
        task2Thread.start();

        //join method:
        task2Thread.join();

        //task 3
        System.out.print("\ntask 3 kicked off");
        System.out.print("\ntask 3 started");
        for (int i=300;i<=399;i++){
            System.out.print(i + " ");
        }
        System.out.print("\ntask 3 done");
    }
}
