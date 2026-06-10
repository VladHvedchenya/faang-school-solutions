package Multithreading.Task15;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Game {
    private int score = 0;
    private int lives = 10;
    private final Object scoreLock = new Object();
    private final Object livesLock = new Object();
    private ExecutorService executor;

    public void update(GameEventType lisneter) {
        String playerName = Thread.currentThread().getName();

        if (lisneter == GameEventType.ADD_SCORE) {
            synchronized (scoreLock) {
                score++;
                System.out.printf("[ИГРА] %s набрал очки! Командный счет: %d\n", playerName, score);
            }
        } else if (lisneter == GameEventType.LOSE_LIFE) {
            synchronized (livesLock) {
                if(lives > 0){
                    lives--;
                    System.out.printf("[ИГРА] %s потерял жизнь! Осталось жизней: %d\n", playerName, lives);
                    if (lives == 0)
                        gameOver();
                }
                            }
        }
    }

    public void start() {
        List<Player> players = List.of(
                new Player("Greg", this::update),
                new Player("Lucy", this::update));

        executor = Executors.newFixedThreadPool(players.size()); //maxPlayerCount is 4 so create 4 threads if fine here.

        players.forEach(executor::submit);
        executor.shutdown();

        try {
            if (!executor.awaitTermination(5, TimeUnit.MINUTES)) {
                executor.shutdownNow();
                System.out.println("Долгая игра.");
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
            System.out.println("Поток был прерван!");
        }

        System.out.println("<--- GAME OVER --->");
    }

    private void gameOver(){
        executor.shutdownNow();
    }
}