package Multithreading.Task19;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Application {
    public static void main(String[] args){
        House house = new House(5);
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(5);
        house.setExecutor(executor);
        System.out.println("<--- ТОМ И ДЖЕРРИ НАЧИНАЮТ ОХОТУ ЗА ЕДОЙ --->\n");

        executor.scheduleAtFixedRate(house::collectFood, 0, 3000, TimeUnit.MILLISECONDS);

        try{
            if(!executor.awaitTermination(5, TimeUnit.MINUTES)){
                executor.shutdownNow();
            }
        }
        catch (InterruptedException e){
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }

        System.out.println("\n🏁 Еда в доме собрана! Операция завершена успешно.");
    }
}