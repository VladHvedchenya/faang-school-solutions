package Multithreading.Task29;

import lombok.Getter;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Kingdom {
    @Getter
    private final String name;

    private final List<String> receivedMessages = new CopyOnWriteArrayList<>();

    public Kingdom(String name) {
        this.name = name;
    }

    public CompletableFuture<String> sendMessage(Kingdom receiver, String message) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);

                if (ThreadLocalRandom.current().nextDouble() < 0.3) {
                    throw new RuntimeException("Ворон был сбит стрелой над замком " + receiver.getName());
                }

                receiver.receiveMessage(message);
                return String.format("[УСПЕХ] Ворон успешно долетел из %s в %s", this.name, receiver.getName());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Полет прерван", e);
            }
        });
    }

    public void showReceivedMessages() {
        System.out.printf("%n📜 Полученные письма королевства %s:%n", name);

        if (receivedMessages.isEmpty()) {
            System.out.println("   [Пусто] Ни один ворон не прорвался.");
        }
        for (var message : receivedMessages) {
            System.out.println("   - " + message);
        }
    }

    private void receiveMessage(String message) {
        receivedMessages.add(message);
    }
}