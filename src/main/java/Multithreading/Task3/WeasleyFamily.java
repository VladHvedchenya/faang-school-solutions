package Multithreading.Task3;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class WeasleyFamily {
    private final List<Chore> chores;

    public WeasleyFamily(List<Chore> chores){
        //null and empty checks for list type!
        this.chores = chores;
    }

    public void work(){
        ExecutorService executor = Executors.newCachedThreadPool();
        chores.stream()
                .map(ChoreRunnable::new)
                .forEach(executor::submit);

        executor.shutdown();

        try{
            if(!executor.awaitTermination(10, TimeUnit.MINUTES)){
                System.err.println("Уизли не успели сделать уборку вовремя!");
                executor.shutdownNow();
            }
        }
        catch (InterruptedException e){
            System.err.println("Главный поток прерван");
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }

        System.out.println("\n[МАГИЯ] Все домашние дела Перси успешно автоматизированы и выполнены!");
    }
}