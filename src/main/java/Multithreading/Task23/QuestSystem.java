package Multithreading.Task23;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class QuestSystem {
    private final List<Quest> quests;

    public QuestSystem() {
        quests = new ArrayList<>();
        quests.add(new Quest("Поворот не туда!"));
        quests.add(new Quest("Едкий запах!"));
        quests.add(new Quest("Подсказки свыше!"));
        quests.add(new Quest("Демон ночи!"));
        quests.add(new Quest("Загадочная пропасть!"));
        quests.add(new Quest("Привет из прошлого!"));
    }

    public CompletableFuture<Player> startQuest(Player player) {
        return CompletableFuture.supplyAsync(() -> {
            quests.forEach(quest -> runSingleQuest(player, quest));
            return player;
        });
    }

    private void runSingleQuest(Player player, Quest quest) {
        System.out.println("⚔️ Игрок [" + player.getName() + "] начал квест: " + quest.getTitle());

        try {
            Thread.sleep(quest.getDifficulty() * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        player.setExperience(player.getExperience() + quest.getReward());
        System.out.println("✅ Игрок [" + player.getName() + "] завершил квест: " + quest.getTitle() + " и получил " + quest.getReward() + " опыта.");
    }
}