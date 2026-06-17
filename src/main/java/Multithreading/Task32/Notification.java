package Multithreading.Task32;

public record Notification(int id, String message) {
    public Notification{
        if (id <= 0)
            throw new IllegalArgumentException("The illegal id!");
        if (message == null)
            throw new NullPointerException("String type can't be null for notification!");
        if (message.isBlank())
            throw new IllegalArgumentException("The string type can't be empty for notification!");
    }
}