package Multithreading.Task28;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadLocalRandom;

public class Arena {
    private final Queue<Robot> robots;
    private final ExecutorService executor;

    public Arena(ExecutorService executor){
        //null & empty checks for executor value
        this.executor = executor;
        robots = new LinkedList<>();
        robots.add(new Robot("Терминатор T-800", 80, 70));
        robots.add(new Robot("Робокоп", 75, 75));
        robots.add(new Robot("Оптимус Прайм", 95, 85));
        robots.add(new Robot("Мегатрон", 98, 80));
        robots.add(new Robot("Бендер (Футурама)", 40, 50));
        robots.add(new Robot("ВАЛЛ-И", 30, 90));
        robots.add(new Robot("Генерал Гривус", 90, 65));
        robots.add(new Robot("Дройдека", 70, 80));
    }

    public void startTournament(){
        List<CompletableFuture<Robot>> grid = robots.stream().map(CompletableFuture::completedFuture).toList();

        while(grid.size() > 1){
            List<CompletableFuture<Robot>> nextRound = new ArrayList<>();

            for (int i = 0; i < grid.size(); i += 2) {
                CompletableFuture<Robot> first = grid.get(i);
                CompletableFuture<Robot> second = grid.get(i + 1);
                CompletableFuture<Robot> pairWinner = first.thenCombineAsync(second, this::fight, executor);
                nextRound.add(pairWinner);
            }

            grid = nextRound;
        }

        System.out.println("🤖 Пайплайн построен. Главный поток main блокируется один раз и ждет финала...\n");
        Robot winner = grid.getFirst().join();
        System.out.printf("\n🏆 АБСОЛЮТНЫЙ ЧЕМПИОН АРЕНЫ: %s!\n", winner.name());
        executor.shutdown();
    }

    private Robot fight(Robot first, Robot second){
        //robots checks for null
        try{
            Thread.sleep(1500);
            int firstStats = first.attackPower() - first.defencePower();
            int secondStats = second.attackPower() - second.defencePower();

            if(firstStats > secondStats)
                return first;
            else if(secondStats > firstStats)
                return second;
            else
                return ThreadLocalRandom.current().nextBoolean() ? first : second;
        }
        catch (InterruptedException e){
            Thread.currentThread().interrupt();
            return ThreadLocalRandom.current().nextBoolean() ? first : second;
        }
    }
}