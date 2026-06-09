package Multithreading.Task11;

import lombok.Getter;
import lombok.Setter;

public class RocketLaunch implements Runnable {
    private final String name;
    @Getter
    private final long launchTime;
    @Setter
    private long programStartTime;

    public RocketLaunch(String name, long launchTime, long programStartTime) {
        this.programStartTime = programStartTime;
        if (name == null)
            throw new NullPointerException("The name can't be null");
        if (name.isEmpty())
            throw new IllegalArgumentException("The name can't be empty");
        this.name = name;
        this.launchTime = launchTime;
    }

    @Override
    public void run() {
        long currentSystemTime = System.currentTimeMillis();

        long msSinceStart = currentSystemTime - programStartTime;

        long delay = msSinceStart - launchTime;

        System.out.printf("[ПОТОК: %s] 🚀 Ракета %s ПОЛУЧИЛА поток на %d мс от старта (План: %d мс). Задержка из-за очереди: %d мс.%n",
                Thread.currentThread().getName(), name, msSinceStart, launchTime, delay);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Поток был прерван!");
            return;
        }

        long finishTime = System.currentTimeMillis() - programStartTime;
        System.out.printf("   ✨ Ракета %s ОКОНЧАТЕЛЬНО УЛЕТЕЛА на %d мс от старта. Поток освободился!%n", name, finishTime);
    }
}