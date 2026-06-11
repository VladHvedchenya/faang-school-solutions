package Multithreading.Task19;

import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;

public class House {
    private final List<Room> rooms;
    private final List<Product> products = new ArrayList<>();
    @Setter
    private ScheduledExecutorService executor;

    public House(int roomCount){
        if (roomCount <= 0)
            throw new IllegalArgumentException("Count of rooms should be > 0");
        rooms = new ArrayList<>(roomCount);
        generateRooms(roomCount);
    }

    public void collectFood(){
        if (isEmpty()){
            if (!executor.isShutdown()) {
                System.out.println("\n🎉 [ПЛАНИРОВЩИК] Еда в доме полностью закончилась! Останавливаем операцию.");
                executor.shutdown();
            }
            return;
        }

        int firstRoomId = ThreadLocalRandom.current().nextInt(0, rooms.size());
        int secondRoomId = ThreadLocalRandom.current().nextInt(0, rooms.size());

        while (firstRoomId == secondRoomId) {
            secondRoomId = ThreadLocalRandom.current().nextInt(0, rooms.size());
        }

        //to remove Deadlock!
        int firstId = Math.min(firstRoomId, secondRoomId);
        int secondId = Math.max(firstRoomId, secondRoomId);

        Room first = rooms.get(firstId);
        Room second = rooms.get(secondId);

        List<Product> currentRaidFood = new ArrayList<>();

        synchronized (first) {
            synchronized (second) {
                currentRaidFood.addAll(first.collectProducts());
                currentRaidFood.addAll(second.collectProducts());
            }
        }

        if (!currentRaidFood.isEmpty()) {
            synchronized (products) {
                products.addAll(currentRaidFood);
                System.out.printf("[%s] 🧀 Успешный рейд! Обыскали комнаты #%d и #%d. Найдено: %d шт. Всего в мешке: %d%n",
                        Thread.currentThread().getName(), firstId, secondId, currentRaidFood.size(), products.size());
            }
        }
    }

    private boolean isEmpty(){
        return rooms.stream().allMatch(Room::isEmpty);
    }

    private void generateRooms(int roomCount){
        for (int i = 0; i < roomCount; i++) {
            rooms.add(new Room());
        }
    }
}