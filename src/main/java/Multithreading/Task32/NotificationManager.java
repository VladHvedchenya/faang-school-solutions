package Multithreading.Task32;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class NotificationManager {
    private final List<Notification> notifications;

    public NotificationManager() {
        notifications = new ArrayList<>();
    }

    public void addNotification(Notification notification) {
        synchronized (notifications) {
            notifications.add(notification);
        }
    }

    public CompletableFuture<Void> fetchNotification(int id, String message) {
        return CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(1000);
                System.out.println("Creating notification...");
                Notification notification = new Notification(id, message);
                addNotification(notification);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException();
            }
        });
    }

    public void showNotifications() {
        synchronized (notifications) {
            notifications.forEach(n -> System.out.println(n.id() + " " + n.message()));
        }
    }
}