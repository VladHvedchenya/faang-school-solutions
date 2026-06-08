package Multithreading.Task11;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SpaceX {
    public static void main(String[] args){
        List<RocketLaunch> rocketLaunches = List.of(
                new RocketLaunch("Белая", 1000, System.currentTimeMillis()),
                new RocketLaunch("Черная", 1500, System.currentTimeMillis()),
                new RocketLaunch("Зеленая", 2000, System.currentTimeMillis())
        );

        planRockerLaunches(rocketLaunches);
    }

    public static void planRockerLaunches(List<RocketLaunch> launches){
        long startTime = System.currentTimeMillis();
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

        for (var launch : launches){
            launch.setProgramStartTime(startTime);
            executor.schedule(launch, launch.getLaunchTime(), TimeUnit.MILLISECONDS);
        }

        executor.shutdown();

        try{
            if(!executor.awaitTermination(1, TimeUnit.MINUTES)){
                executor.shutdownNow();
                System.out.println("Слишком работали потоки.");
            }
        }
        catch (InterruptedException e){
            executor.shutdownNow();
            Thread.currentThread().interrupt();
            System.out.println("Поток был прерван!");
        }

        System.out.println("Все ракеты успешно были отправлены!");
        long endTime = System.currentTimeMillis();

        System.out.println("Время выполнения " + (endTime - startTime));
    }
}