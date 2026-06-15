package Multithreading.Task21;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        ChatManager manager = new ChatManager();
        List<User> users = manager.getInitialUsers();

        System.out.println("=== СИМУЛЯТОР ТИНДЕРА ЗАПУЩЕН ===");

        for (User user : users) {
            Thread userThread = new Thread(() -> {
                while (!Thread.currentThread().isInterrupted()) {
                    manager.startChat(user);

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }, "Поток-" + user.getName());

            userThread.start();
        }
    }
}
