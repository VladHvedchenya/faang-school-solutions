package com.Multithreading.Task6;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TournamentPlace {
    public static void main(String[] args){
        Knight first = new Knight("Garward");
        first.addTrial(new Trial("Забег с верблюдами"));
        first.addTrial(new Trial("Бой с орками"));
        Knight second = new Knight("Barateon");
        second.addTrial(new Trial("Свержение дракона на горе"));
        second.addTrial(new Trial("Вызволение принцессы"));

        ExecutorService tournamentExecutor = Executors.newFixedThreadPool(2);

        first.startTrials(tournamentExecutor);
        second.startTrials(tournamentExecutor);

        tournamentExecutor.shutdown();

        try {
            if (!tournamentExecutor.awaitTermination(10, TimeUnit.MINUTES)) {
                System.out.println("Турнир затянулся, закрываем арену!");
                tournamentExecutor.shutdownNow();
            }
        } catch (InterruptedException e) {
            tournamentExecutor.shutdownNow();
            Thread.currentThread().interrupt();
        }

        System.out.println("\n[ФИНАЛ] Король Роберт доволен, турнир успешно завершен!");
    }
}