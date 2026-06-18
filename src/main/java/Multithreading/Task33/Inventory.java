package Multithreading.Task33;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;

public class Inventory {
    private final List<Item> items = new CopyOnWriteArrayList<>();

    public CompletableFuture<Void> addItem(Item item) {
        return CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(500);
                items.add(item);
                System.out.printf("[ИНВЕНТАРЬ] Предмет '%s' (Сила: %d) успешно уложен в слот!%n",
                        item.name(), item.power());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        });
    }

    public Item combineItems(Item first, Item second) {
        String newName = first.name() + " " + second.name();
        int newPower = first.power() + second.power();
        return new Item(newName, newPower);
    }

    public void showItems(){
        items.forEach(item -> System.out.printf("Название: %s <---> Урон: %s\n", item.name(), item.power()));
    }
}