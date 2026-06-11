package Multithreading.Task20;

import java.util.concurrent.ThreadLocalRandom;

public class Application {
    public static void main(String[] args){
        TelegramBot bot = new TelegramBot();

        System.out.println("=== ЗАПУСК БОТА В РЕЖИМЕ ОНЛАЙН-МОНИТОРИНГА ===");
        System.out.println("Для остановки нажмите Ctrl+C в терминале.\n");

        int messageId = 1;
        while (true) {
            final int currentId = messageId++;

            Thread userThread = new Thread(() -> {
                bot.sendMessage("Текст сообщения №" + currentId);
            }, "Поток-Юзер-" + currentId);

            userThread.start();

            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(200) + 50);
            } catch (InterruptedException e) {
                System.out.println("Генерация потоков прервана.");
                break;
            }
        }
    }
}
