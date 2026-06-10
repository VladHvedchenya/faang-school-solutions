package Multithreading.Task17;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Application {
    public static int USER_COUNT = 8;

    public static void main(String[] args){
        House house = new House();
        ExecutorService executor = Executors.newFixedThreadPool(USER_COUNT);

        List<User> users = List.of(
                new User("Greg", Role.KNIGHT),
                new User("Lucy", Role.KNIGHT), // Будет ждать
                new User("Sam", Role.KNIGHT),  // Будет ждать
                new User("John", Role.TARGARIAN),
                new User("Anna", Role.ARCHE)
        );

        for (var user : users){
            executor.submit(() -> user.joinHouse(house));
        }

        executor.shutdown();

        try{
            if(!executor.awaitTermination(5, TimeUnit.MINUTES)){
                executor.shutdownNow();
            }
        }
        catch (InterruptedException e){
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
