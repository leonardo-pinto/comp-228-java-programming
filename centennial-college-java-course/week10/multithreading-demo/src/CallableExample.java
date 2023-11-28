import java.util.List;
import java.util.concurrent.*;

class CallableTask implements Callable<String>{
    private String name;

    public CallableTask(String name) {
        this.name = name;
    }

    @Override
    public String call() throws Exception {
        return "Good Evening " + name;
    }
}

public class CallableExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        //ctrl + alt + v - store into local variable
        //Future is promise that there will be result
        List<CallableTask> callableTaskList = List.of(new CallableTask("Leonardo"), new CallableTask("Dmytro"), new CallableTask("Pouya"));
        String greetingMessage = executorService.invokeAny(callableTaskList);

        //get value from future and store it into string variable
        System.out.println(greetingMessage);

        executorService.shutdown();
    }
}
