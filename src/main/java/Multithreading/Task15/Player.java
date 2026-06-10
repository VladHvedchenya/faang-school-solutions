package Multithreading.Task15;

import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;

public class Player implements Runnable {
    private final String name;
    private final Consumer<GameEventType> listener;

    public Player(String name, Consumer<GameEventType> listener) {
        if (name == null)
            throw new NullPointerException("The name can't be null");
        if (name.isEmpty())
            throw new IllegalArgumentException("The name can't be empty");

        this.listener = listener;
        this.name = name;
    }

    @Override
    public void run() {
        Thread.currentThread().setName(name);

        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }

            GameEventType event = GameEventType.values()[ThreadLocalRandom.current().nextInt(0, GameEventType.values().length)];

            listener.accept(event);
        }

        System.out.printf("Поток [Бро %s] сложил оружие и вышел из боя.\n", name);
    }
}