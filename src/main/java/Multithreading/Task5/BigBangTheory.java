package Multithreading.Task5;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class BigBangTheory {
    public static void main(String[] args) {
        List<Task> tasks = List.of(
                new Task("Sheldon", "Preparing theory"),
                new Task("Leonard", "Modeling experiment"),
                new Task("Govard", "Develoing instruments"),
                new Task("Radzhesh", "Analyzing data")
        );

        ExecutorService executor = Executors.newFixedThreadPool(tasks.size());

        tasks.forEach(executor::submit);
        executor.shutdown();

        try{
            if(!executor.awaitTermination(10, TimeUnit.MINUTES)){
                System.err.println("Компания не успела выполнить в срок.");
                executor.shutdownNow();
            }
        }
        catch (InterruptedException e){
            System.err.println("Поток был прерван.");
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}