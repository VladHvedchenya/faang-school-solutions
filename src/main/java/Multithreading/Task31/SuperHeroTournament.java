package Multithreading.Task31;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

public class SuperHeroTournament {
    private final List<SuperHero> heroes;

    public SuperHeroTournament(List<SuperHero> heroes) {
        //null &empty checks
        this.heroes = heroes;
    }

    public void runCompetition() {
        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();
        List<CompletableFuture<SuperHero>> grid = heroes.stream().map(CompletableFuture::completedFuture).toList();

        while (grid.size() > 1) {
            List<CompletableFuture<SuperHero>> nextRoundWinners = new ArrayList<>();

            for (int i = 0; i < grid.size(); i += 2) {
                CompletableFuture<SuperHero> first = grid.get(i);

                if (i + 1 < grid.size()) {
                    CompletableFuture<SuperHero> second = grid.get(i + 1);
                    CompletableFuture<SuperHero> combined = first.thenCombineAsync(second, this::fight, executor);
                    nextRoundWinners.add(combined);
                } else {
                    nextRoundWinners.add(first);
                }
            }

            grid = nextRoundWinners;
        }

        SuperHero winner = grid.getFirst().join();
        System.out.printf("The Winner is: %s", winner.name());
        executor.shutdown();
    }

    private SuperHero fight(SuperHero first, SuperHero second) {
        try {
            Thread.sleep(1000);
            int firstStats = first.agility() + first.strength();
            int secondStats = second.agility() + second.strength();

            if (firstStats == secondStats)
                return ThreadLocalRandom.current().nextBoolean() ? first : second;
            return firstStats > secondStats ? first : second;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException();
        }
    }
}