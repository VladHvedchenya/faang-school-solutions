package Multithreading.Task4;

import java.util.concurrent.ThreadLocalRandom;

public class FoodDelivery implements Runnable {
    private final String name;

    public FoodDelivery(String name) {
        //null and empty checks for string type
        this.name = name;
    }

    @Override
    public void run() {
        FoodType type = generateFoodType();
        int foodCount = generateFoodCount();
        System.out.println(name + " получает " + type + " в количестве " + foodCount + " и сразу начинает есть!");

        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 5000));
        } catch (InterruptedException e) {
            System.out.println("Поток " + Thread.currentThread().getName() + " был прерван!");
            Thread.currentThread().interrupt();
        }

        System.out.println(Thread.currentThread().getName() + " ЗАВЕРШИЛ кормление: " + name);
    }

    private FoodType generateFoodType(){
        return FoodType.values()[ThreadLocalRandom.current().nextInt(FoodType.values().length)];
    }

    private int generateFoodCount(){
        return ThreadLocalRandom.current().nextInt(0, 6);
    }
}