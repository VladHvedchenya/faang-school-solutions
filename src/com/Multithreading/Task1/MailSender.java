package com.Multithreading.Task1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class MailSender {
    void main() {
        List<Letter> letters = IntStream.range(0, 1000)
                .mapToObj(index -> new Letter("Письмо " + index))
                .toList();
        int totalLetterCount = letters.size();
        int threadCount = 5;
        int lettersPerThread = totalLetterCount / threadCount;

        ExecutorService executor = Executors.newFixedThreadPool(threadCount);

        for (int i = 0; i < threadCount; i++) {
            int startIndex = i * lettersPerThread;
            int endIndex = startIndex + lettersPerThread;

            List<Letter> subList = letters.subList(startIndex, endIndex);
            executor.submit(new SenderRunnable(subList));
        }

        executor.shutdown();

        try {
            if(!executor.awaitTermination(10, TimeUnit.MINUTES)){
                System.err.println("Потоки не успели завершить работу за определенное время");
            }
        }
        catch (InterruptedException e){
            System.out.println("Поток прервали");
        }

        System.out.println("УСПЕХ, все письма через ExecutorService успешно отправлены!");
//        List<Thread> threads = new ArrayList<>(threadCount);
//
//        for (int i = 0; i < threadCount; i++) {
//            int startIndex = i * lettersPerThread;
//            int endIndex = startIndex + lettersPerThread;
//
//            List<Letter> subList = letters.subList(startIndex, endIndex);
//            SenderRunnable sender = new SenderRunnable(subList);
//            Thread thread = new Thread(sender, "Поток " + i);
//            threads.add(thread);
//            thread.start();
//        }
//
//        try{
//            for (var thread : threads){
//                thread.join();
//            }
//        }
//        catch (InterruptedException e){
//            System.err.println("Поток прерван");
//        }
//
//        System.out.println("УСПЕХ! Все письма успешно отправлены!");
    }
}