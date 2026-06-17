package Multithreading.Task32;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class Application {
    public static void main(String[] args){
        NotificationManager manager = new NotificationManager();

        List<CompletableFuture<Void>> futures = new ArrayList<>();

        Object[][] facebookNotifications = {
                {101, "Марк Цукерберг добавил вас в друзья"},
                {102, "Илон Маск прокомментировал ваш статус: 'Let that sink in!'"},
                {103, "Билл Гейтс пригласил вас на закрытое тестирование новой ОС"},
                {104, "Джефф Безос оценил ваше фото со стратосферы"},
                {105, "Влад Мишустин оставил ревью к вашему пул-реквесту: 'Prod-level!'"},
                {106, "Система безопасности: обнаружен подозрительный вход из Дели"},
                {107, "Ваше воспоминание 5-летней давности готово к просмотру"},
                {108, "Линус Торвальдс упомянул вас в яростном коммите к ядру Linux"}
        };


        for (var notificationInfo : facebookNotifications){
            futures.add(manager.fetchNotification((int)notificationInfo[0], (String) notificationInfo[1]));
        }

        CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new)).join();
        manager.showNotifications();
    }
}
