package Multithreading.Task33;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

public class Application {
    public static void main(String[] args) {
        List<Item> chestPool = getChestPool();
        List<Item> shopPool = getShopPool();

        ExecutorService executor = Executors.newFixedThreadPool(5);
        Inventory inventory = new Inventory();
        List<CompletableFuture<Void>> pipeLines = new ArrayList<>();

        int minPairCount = Math.min(chestPool.size(), shopPool.size());

        for (int i = 0; i < minPairCount; i++) {
            CompletableFuture<Item> shopFuture = CompletableFuture.supplyAsync(() -> getItemFromShop(shopPool), executor);
            CompletableFuture<Item> chestFuture = CompletableFuture.supplyAsync(() -> getItemFromChest(chestPool), executor);
            CompletableFuture<Item> combined = shopFuture.thenCombineAsync(chestFuture, inventory::combineItems, executor);
            CompletableFuture<Void> craftPipeLine = combined.thenCompose(inventory::addItem);
            pipeLines.add(craftPipeLine);
        }

        CompletableFuture.allOf(pipeLines.toArray(CompletableFuture[]::new)).join();
        executor.shutdown();
        inventory.showItems();
    }

    private static @NonNull List<Item> getChestPool() {
        List<Item> chestPool = new CopyOnWriteArrayList<>();
        chestPool.add(new Item("Javelin", 25));
        chestPool.add(new Item("Quarterstaff", 10));
        chestPool.add(new Item("Gloves of Haste", 20));
        chestPool.add(new Item("Boots of Speed", 5));
        chestPool.add(new Item("Belt of Elvenskin", 6));
        chestPool.add(new Item("Band of Elvenskin", 6));
        chestPool.add(new Item("Robe of the Magi", 6));
        chestPool.add(new Item("Blades of Attack", 9));
        chestPool.add(new Item("Chainmail", 4));
        chestPool.add(new Item("Helm of Iron Will", 5));
        return chestPool;
    }

    private static @NonNull List<Item> getShopPool() {
        List<Item> shopPool = new CopyOnWriteArrayList<>();
        shopPool.add(new Item("Mithril Hammer", 24));
        shopPool.add(new Item("Claymore", 21));
        shopPool.add(new Item("Broadsword", 15));
        shopPool.add(new Item("Demon Edge", 40));
        shopPool.add(new Item("Sacred Relic", 60));
        shopPool.add(new Item("Hyperstone", 30));
        shopPool.add(new Item("Ultimate Orb", 15));
        shopPool.add(new Item("Point Booster", 10));
        shopPool.add(new Item("Staff of Wizardry", 10));
        shopPool.add(new Item("Ogre Axe", 10));
        return shopPool;
    }

    private static Item getItemFromChest(List<Item> chestPool) {
        try {
            Thread.sleep(1000);
            System.out.println("Получаю предмет из сундука...");
            Item item = chestPool.get(ThreadLocalRandom.current().nextInt(0, chestPool.size()));
            chestPool.remove(item);
            return item;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException();
        }
    }

    private static Item getItemFromShop(List<Item> shopPool) {
        try {
            Thread.sleep(3000);
            System.out.println("Получаю предмет из магазина...");
            Item item = shopPool.get(ThreadLocalRandom.current().nextInt(0, shopPool.size()));
            shopPool.remove(item);
            return item;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException();
        }
    }
}