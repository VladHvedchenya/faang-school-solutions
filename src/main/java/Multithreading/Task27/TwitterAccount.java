package Multithreading.Task27;

import lombok.Getter;

public class TwitterAccount {
    @Getter
    private final String name;
    @Getter
    private int followerCount;

    public TwitterAccount(String name) {
        //null & empty checks
        this.name = name;
    }

    public synchronized void addFollower(){
        followerCount++;
    }
}