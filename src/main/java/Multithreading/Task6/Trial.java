package Multithreading.Task6;

import java.util.concurrent.ThreadLocalRandom;

public class Trial {
    private final String title;

    public Trial(String title){
        //null & empty check for String type
        this.title = title;
    }

    public void execute(String knightName) {
        System.out.println("Рыцарь " + knightName + " начал испытание: " + title
                + " в потоке " + Thread.currentThread().getName());
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(500, 1000));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("[УСПЕХ] Рыцарь " + knightName + " ЗАВЕРШИЛ: " + title);
    }
}