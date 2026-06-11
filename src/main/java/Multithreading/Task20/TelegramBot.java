package Multithreading.Task20;

import java.util.LinkedList;
import java.util.Queue;

public class TelegramBot {
    private final int REQUEST_LIMIT = 5;
    private final Queue<Long> timeStamps = new LinkedList<>();
    private long lastRequestTime;

    public TelegramBot() {
        this.lastRequestTime = System.currentTimeMillis();
        System.out.println("[BOT] Бот инициализирован. Лимит: " + REQUEST_LIMIT + " запр/сек.");
    }

    public void sendMessage(String message) {
        synchronized (timeStamps) {
            long now = System.currentTimeMillis();

            while (timeStamps.size() >= REQUEST_LIMIT && !Thread.currentThread().isInterrupted()) {
                while (!timeStamps.isEmpty() && (now - timeStamps.peek() >= 1000)) {
                    long removed = timeStamps.poll();
                    System.out.println(String.format(
                            "   [ОЧИСТКА] Запрос от %d устарел (>1000мс). Удален. Текущий counter: %d",
                            removed, timeStamps.size()
                    ));
                }

                int requestCounter = timeStamps.size();

                if (requestCounter >= REQUEST_LIMIT) {
                    long oldestRequestTime = timeStamps.peek();
                    long pause = 1000 - (now - oldestRequestTime);

                    System.out.println(String.format(
                            "❌ [%s] ПРЕВЫШЕН ЛИМИТ! В очереди: %d. Ждем освобождения места %d мс...",
                            Thread.currentThread().getName(), requestCounter, pause
                    ));

                    try {
                        timeStamps.wait(pause);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        throw new RuntimeException(e);
                    }

                    now = System.currentTimeMillis();

                } else {
                    break;
                }
            }

            timeStamps.add(now);
            long durationSinceLastRequest = now - lastRequestTime;
            lastRequestTime = now;

            System.out.println(String.format(
                    "✅ [%s] ПОДТВЕРЖДЕН. Добавлен в очередь. Текущий counter: %d. Прошло с прошлого запроса: %d мс. Сообщение: %s",
                    Thread.currentThread().getName(), timeStamps.size(), durationSinceLastRequest, message
            ));

            timeStamps.notifyAll();
        }
    }
}