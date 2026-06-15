package Multithreading.Task23;

import java.util.concurrent.CompletableFuture;

public class Application {
    public static void main(String[] args){
        QuestSystem questSystem = new QuestSystem();
        Player first = new Player("Vlad");
        Player second = new Player("Mila");
        CompletableFuture<Player> firstQuests = questSystem.startQuest(first);
        CompletableFuture<Player> secondQuests = questSystem.startQuest(second);

        firstQuests.join();
        secondQuests.join();

        System.out.printf("%s завершил все квесты и его суммарный опыт составил %s\n", first.getName(), first.getExperience());
        System.out.printf("%s завершил все квесты и его суммарный опыт составил %s", second.getName(), second.getExperience());
    }
}