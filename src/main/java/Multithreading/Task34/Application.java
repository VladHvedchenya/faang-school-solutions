package Multithreading.Task34;

import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Application {
    public static void main(String[] args){
        List<Integer> numbers = List.of(200000);
        Factorial factorial = new Factorial();
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        List<CompletableFuture<BigInteger>> futures = factorial.calculateFactorials(numbers, executor);
        AtomicInteger counter = new AtomicInteger(numbers.size());

        for (var future : futures){
            future.thenAccept(result -> {
                System.out.println(result);
                counter.decrementAndGet();
            });
        }

        while (counter.get() > 0){
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("\n🏁 Все асинхронные вычисления успешно завершены!");
        executor.shutdown();
    }
}
