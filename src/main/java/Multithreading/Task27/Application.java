package Multithreading.Task27;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class Application {
    public static void main(String[] args){
        TwitterAccount account = new TwitterAccount("Vlad");
        TwitterSubscriptionSystem system = new TwitterSubscriptionSystem();
        List<CompletableFuture<Void>> tasks = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            CompletableFuture<Void> task = system.followAccount(account);
            tasks.add(task);
        }

        CompletableFuture.allOf(tasks.toArray(CompletableFuture[]::new)).join();

        System.out.printf("Общее количество подписчиков: %s", account.getFollowerCount());
    }
}