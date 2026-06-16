package Multithreading.Task27;

import java.util.concurrent.CompletableFuture;

public class TwitterSubscriptionSystem {
    public void addFollower(TwitterAccount account){
        if (account == null)
            throw new NullPointerException("The account can't be null");
        account.addFollower();
    }

    public CompletableFuture<Void> followAccount(TwitterAccount account){
        return CompletableFuture.runAsync(() -> addFollower(account));
    }
}