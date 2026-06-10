package Multithreading.Task16;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Application {
    public static final int NUM_THREAD = 3;
    public static final int NUM_VIDEOS = 4;

    public static void main(String[] args){
        VideoManager manager = new VideoManager();
        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREAD);

        for (int i = 0; i < NUM_VIDEOS; i++) {
            String videoId = "video_" + UUID.randomUUID().toString().substring(0, 5);

            for (int j = 0; j < NUM_THREAD; j++) {
                executor.submit(() -> manager.addVideo(videoId));
            }
        }

        executor.shutdown();

        try{
            if(!executor.awaitTermination(5, TimeUnit.MINUTES)){
                executor.shutdownNow();
                System.out.println("Поток был прерван из-за времени выполнения программы");
            }
        }
        catch (InterruptedException e){
            executor.shutdownNow();
            Thread.currentThread().interrupt();
            System.out.println("Поток прерван.");
        }
    }
}