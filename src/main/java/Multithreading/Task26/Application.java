package Multithreading.Task26;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class Application {
    public static void main(String[] args) {
        OrderProcessor processor = new OrderProcessor();

        List<Order> orders = List.of(
                new Order(101),
                new Order(102),
                new Order(103),
                new Order(104),
                new Order(105)
        );

        CompletableFuture<?>[] futures = orders.stream()
                .map(order -> CompletableFuture.runAsync(() -> processor.processOrder(order)))
                .toArray(CompletableFuture[]::new);
        CompletableFuture.allOf(futures).join();

        System.out.printf("Количество обработанных заказов: %s", processor.getTotalProcessedOrders());
    }
}