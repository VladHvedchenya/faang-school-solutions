package Multithreading.Task22;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Application {
    private static final MasterCardService service = new MasterCardService();
    private static final ExecutorService executor = Executors.newSingleThreadExecutor();

    public static void main(String[] args){
        doAll();
    }

    public static void doAll(){
        Future<Integer> collect = executor.submit(service::collectPayment);
        CompletableFuture<Integer> analise = CompletableFuture.supplyAsync(service::sendAnalytics);

        System.out.printf("Аналитика отправлена: %s\n", analise.join());

        try {
            System.out.printf("Платеж выполнен: %s", collect.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        finally{
            executor.shutdown();
        }
    }
}