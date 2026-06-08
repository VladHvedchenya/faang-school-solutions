package LambdaExpressions.Task2;

public class Application {
    void main(){
        NotificationManager manager = new NotificationManager();
        manager.registerHandler(NotificationType.EMAIL, notification ->
                System.out.println("SENDING EMAIL " + notification.getMessage()));
        manager.registerHandler(NotificationType.SMS, notification ->
                System.out.println("SENDING SMS " + notification.getMessage()));
        manager.sendNotification(new Notification(NotificationType.EMAIL, "ОТПРАВЛЯЮ ВАМ СМСКУ!!!"));
        manager.sendNotification(new Notification(NotificationType.SMS, "НУ ТЫ И ДУРАК"));
        manager.sendNotification(new Notification(NotificationType.PUSH, ""));
    }
}
