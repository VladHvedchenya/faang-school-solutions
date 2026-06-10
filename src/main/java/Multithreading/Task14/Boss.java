package Multithreading.Task14;

import java.sql.SQLOutput;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class Boss {
    private final List<Player> players;
    private final int maxPlayerCount;
    private final Object lock = new Object();

    public Boss(int maxPlayerCount, List<Player> players) {
        if (maxPlayerCount <= 0)
            throw new IllegalArgumentException("The number of players should be >= 1");
        if (players == null)
            throw new NullPointerException("The list of players can't be null");
        this.players = players;
        this.maxPlayerCount = maxPlayerCount;
    }

    public void tryConnectToBattle() {
        Player player = null;

        while (!Thread.currentThread().isInterrupted()){
            synchronized (lock){
                while(players.size() >= maxPlayerCount){
                    try {
                        lock.wait();
                        System.out.println("[Добавлятор] Места нет, жду у двери!");
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
            }

            if (player == null){
                player = generatePlayer();

                try {
                    Thread.sleep(3000);
                    System.out.println("Генерируем игрока и пытаемся подключиться к арене...");
                }
                catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                    System.out.println("Поток был прерван!");
                }
            }

            synchronized (lock){
                if (players.size() < maxPlayerCount) {
                    players.add(player);
                    System.out.printf("[Добавлятор] игрок %s присоединяется к битве! (Всего: %d/%d)%n",
                            player.getName(), players.size(), maxPlayerCount);
                    player = null;
                } else {
                    System.out.printf("[Добавлятор] Игрок %s опоздал, место снова заняли. Уходим на круг ожидания...%n", player.getName());
                }
            }
        }
    }

    public void leaveBattle() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(1000);
                System.out.println("[Удалятор] Пытаюсь удалить игрока...");
            }
            catch (InterruptedException e){
                Thread.currentThread().interrupt();
                System.out.println("Поток удаления прерван!");
                return;
            }

            synchronized (lock){
                if (players.isEmpty()){
                    System.out.println("[Добавлятор] На арене пока никого нет, ждем...");
                    continue;
                }

                Player player = players.get(ThreadLocalRandom.current().nextInt(0, players.size()));
                players.remove(player);
                System.out.printf("[Удалятор] удаляю случайного игрока у босса! Удалили %s \n", player.getName());
                lock.notify();
            }
        }
    }

    private Player generatePlayer() {
        return new Player(UUID.randomUUID().toString().substring(0, 5));
    }
}