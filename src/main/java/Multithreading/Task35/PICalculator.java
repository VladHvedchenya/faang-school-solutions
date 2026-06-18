package Multithreading.Task35;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

public class PICalculator {
    private final int threadCount = Runtime.getRuntime().availableProcessors();
    private final ExecutorService executor = Executors.newFixedThreadPool(threadCount);

    public double calculatePI(long n){
        long pointPerBatch = n / threadCount;
        List<CompletableFuture<Long>> futures = new ArrayList<>();

        for (int i = 0; i < threadCount; i++) {
            CompletableFuture<Long> future = CompletableFuture.supplyAsync(() -> {
                long insideCount = 0;
                ThreadLocalRandom random = ThreadLocalRandom.current();

                for (long j = 0; j < pointPerBatch; j++) {
                    double x = random.nextDouble();
                    double y = random.nextDouble();

                    if (isInside(x, y))
                        insideCount++;
                }

                return insideCount;
            }, executor);

            futures.add(future);
        }

        CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new)).join();
        executor.shutdown();
        long totalInsideCount = futures.stream().mapToLong(CompletableFuture::join).sum();

        return 4.0 * totalInsideCount / n;
    }

    public boolean isInside(double x, double y){
        return (x * x) + (y * y) <= 1;
    }
}