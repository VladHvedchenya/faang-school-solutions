package com.Multithreading.Task5;

import java.util.concurrent.ThreadLocalRandom;

public class Task implements Runnable {
    private final String name;
    private final String context;

    public Task(String name, String context){
        //null and empty checks for String type
        this.name = name;
        this.context = context;
    }
    @Override
    public void run() {
        System.out.println("Поток " + Thread.currentThread().getName());
        System.out.println(name + " принимается за " + context);

        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(1000));
        } catch (InterruptedException e) {
            System.err.println("Рабочий внутренний поток был прерван!");
            Thread.currentThread().interrupt();
        }
    }
}