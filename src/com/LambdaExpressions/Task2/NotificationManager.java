package com.LambdaExpressions.Task2;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class NotificationManager {
    private Map<NotificationType, Consumer<Notification>> handlers = new HashMap<>();

    public void registerHandler(NotificationType type, Consumer<Notification> handler){
        handlers.put(type, handler);
    }


    public void sendNotification(Notification notification){
        Consumer<Notification> handler = handlers.get(notification.getType());
        Predicate<Notification> filter = n -> {
          return !n.getMessage().toLowerCase().contains("дурак");
        };

        Function<Notification, Notification> changer = n -> {
            return new Notification(n.getType(), n.getMessage() + " | Google Inc.");
        };
        changer.apply(notification);
        if (!filter.test(notification)){
            System.out.println("The notification was blocked by filter!");
            return;
        }

        if (handler != null){
            handler.accept(changer.apply(notification));
        }
        else{
            System.out.println("No handler for type: " + notification.getType());
        }
    }
}
