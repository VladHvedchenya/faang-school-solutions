package com.Multithreading.Task7;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Application {
    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();
        int threadCount = Runtime.getRuntime().availableProcessors() * 2;
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        int batch = (int) Math.ceil(people.size() / (double) threadCount);
        List<List<Person>> groups = IntStream.range(0, people.size())
                .boxed()
                .collect(Collectors.groupingBy(
                        index -> index / batch,
                        Collectors.mapping(people::get, Collectors.toList())
                ))
                .values()
                .stream()
                .toList();
        groups.forEach(group -> executor.submit(new PersonInfoPrinter(group)));
        executor.shutdown();

        try{
            if(!executor.awaitTermination(10, TimeUnit.MINUTES)){
                executor.shutdownNow();
                System.out.println("Мы не успели обработать всех");
            }
        }
        catch (InterruptedException e){
            executor.shutdownNow();
            Thread.currentThread().interrupt();
            System.out.println("Поток был прерван");
        }

        System.out.println("Мы успешно обработали всех людей!");
    }
}