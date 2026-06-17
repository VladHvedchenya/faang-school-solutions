package Multithreading.Task30;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Application {
    public static void main(String[] args) {
        List<SquareRequest> requests = new java.util.ArrayList<>(1000);
        ResultConsumer resultConsumer = new ResultConsumer(0L);

        for (long i = 1; i <= 1000; i++) {
            requests.add(new SquareRequest(i));
        }

        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            Long res = fanOutFanIn(requests, resultConsumer, executor);
            System.out.printf("Результат суммы квадратов: %d%n", res);
        }
    }

    public static Long fanOutFanIn(List<SquareRequest> requests, ResultConsumer resultConsumer, ExecutorService executor) {
        List<CompletableFuture<Void>> futures = new ArrayList<>();

        for (var request : requests) {
            futures.add(CompletableFuture.runAsync(() -> request.longTimeSquare(resultConsumer), executor));
        }

        CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new)).join();

        return resultConsumer.getSum();
    }
}