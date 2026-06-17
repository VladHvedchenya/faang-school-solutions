package Multithreading.Task29;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class Application {
    public static void main(String[] args) {
        List<Kingdom> kingdoms = new ArrayList<>();
        kingdoms.add(new Kingdom("Винтерфелл"));
        kingdoms.add(new Kingdom("Королевская Гавань"));
        kingdoms.add(new Kingdom("Драконий Камень"));
        kingdoms.add(new Kingdom("Утес Кастерли"));

        List<CompletableFuture<String>> futures = new ArrayList<>();

        System.out.println("🦅 Выпускаем разведывательных воронов... 🦅\n");

        for (var sender : kingdoms) {
            for (var receiver : kingdoms) {
                if (sender == receiver)
                    continue;
                String message = String.format("Тайное донесение для %s от %s", receiver.getName(), sender.getName());
                CompletableFuture<String> future = sender.sendMessage(receiver, message);
                CompletableFuture<String> handledFuture = future.handle((success, error) -> {
                    if (error != null) {
                        String rawError = error.getCause() != null ? error.getCause().getMessage() : error.getMessage();
                        String errorLog = String.format("❌ [КАТАСТРОФА] Провал отправки из %s: %s", sender.getName(), rawError);
                        System.out.println(errorLog);
                        return errorLog;
                    }

                    System.out.println("Доставка выполнена успешно!");
                    return success;
                });

                futures.add(handledFuture);
            }
        }

        CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new)).join();

        System.out.println("\n==================================================");
        System.out.println("🏁 Все полеты завершены. Проверяем почтовые башни:");
        System.out.println("==================================================");

        for (var kingdom : kingdoms) {
            kingdom.showReceivedMessages();
        }
    }
}