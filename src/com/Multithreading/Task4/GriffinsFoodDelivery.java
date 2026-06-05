package com.Multithreading.Task4;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class GriffinsFoodDelivery {
    public static void main(String[] args){
        String[] names = {"Peter", "Lois", "Meg", "Chris", "Stewie"};
        ExecutorService executor = Executors.newFixedThreadPool(3);
        Arrays.stream(names)
                .forEach(name -> executor.submit(new FoodDelivery(name)));
        executor.shutdown();

        try{
            if(!executor.awaitTermination(10, TimeUnit.MINUTES)){
                System.out.println("Слишком долгая доставка!");
                executor.shutdownNow();
            }
        }
        catch (InterruptedException e){
            System.out.println("Поток был прерван!");
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }

        System.out.println("Доставка для всех гриффинов была выполнена успешно, все сыты!");
    }
}