package Multithreading.Task26;

import java.util.concurrent.atomic.AtomicInteger;

public class OrderProcessor {
    private final AtomicInteger  totalProcessedOrders = new AtomicInteger(0);

    public void processOrder(Order order){
        try{
            Thread.sleep(1500);
        }
        catch (InterruptedException e){
            Thread.currentThread().interrupt();
            throw new RuntimeException();
        }

        order.setProcessed(true);
        totalProcessedOrders.incrementAndGet();
    }

    public int getTotalProcessedOrders(){
        return totalProcessedOrders.get();
    }
}