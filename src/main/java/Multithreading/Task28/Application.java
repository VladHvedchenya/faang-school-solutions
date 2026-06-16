package Multithreading.Task28;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Application {
    public static void main(String[] args){
        ExecutorService executor = Executors.newFixedThreadPool(4);
        Arena arena = new Arena(executor);
        arena.startTournament();
    }
}