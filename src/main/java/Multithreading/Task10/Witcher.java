package Multithreading.Task10;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Witcher {
    public static void main(String[] args){
        List<City> cities = generateCities(50);
        List<Monster> monsters = generateMonsters(200);

        System.out.println("=== МАГИЧЕСКАЯ КАРТА ВЕДЬМАКА АКТИВИРОВАНА ===");
        System.out.printf("Загружено городов: %d, Монстров во всем мире: %d%n", cities.size(), monsters.size());

        runTest(cities, monsters, 1);
        runTest(cities, monsters, 2);
        runTest(cities, monsters, 4);
        runTest(cities, monsters, 8);
    }

    private static void runTest(List<City> cities, List<Monster> monsters, int threadCount){
        long startTime = System.currentTimeMillis();
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        Location castle = new Location(0, 0);
        List<Future<Map<Monster, Double>>> futures = new ArrayList<>();

        for (var city : cities){
            double distanceFromCastle = castle.distanceTo(city.location());
            CityWorker worker = new CityWorker(city, monsters, distanceFromCastle);
            futures.add(executor.submit(worker));
        }

        Map<Monster, Double> allPossibleRoutes = new HashMap<>();

        try {
            for (Future<Map<Monster, Double>> future : futures) {
                Map<Monster, Double> cityResult = future.get();
                allPossibleRoutes.putAll(cityResult);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        executor.shutdown();

        try{
            if(!executor.awaitTermination(10, TimeUnit.MINUTES)){
                executor.shutdownNow();
                System.out.println("Поток был прерван!");
            }
        }
        catch (InterruptedException e){
            executor.shutdownNow();
            Thread.currentThread().interrupt();
            System.out.println("Главный поток был прерван");
        }

        if(!allPossibleRoutes.isEmpty()){
            Map.Entry<Monster, Double> bestContract = allPossibleRoutes
                    .entrySet()
                    .stream()
                    .min(Map.Entry.comparingByValue())
                    .orElseThrow();

            System.out.printf("Идеальный контракт для Геральта: %s. Полный путь займет: %.2f км.%n",
                    bestContract.getKey().name(), bestContract.getValue());
        }

        long endTime = System.currentTimeMillis();
        System.out.printf(">> Время выполнения при %d пот.: %d мс <<%n", threadCount, (endTime - startTime));
    }

    private static List<City> generateCities(int count) {
        List<City> list = new ArrayList<>();
        String[] prefixes = {"Вызима", "Новиград", "Оксенфурт", "Боклер", "Каэр-Морхен", "Флотзам", "Венгерберг", "Лок-Муинне"};
        Random rand = new Random(42);

        for (int i = 1; i <= count; i++) {
            String name = prefixes[rand.nextInt(prefixes.length)] + " #" + i;
            double x = rand.nextDouble() * 500 + 10;
            double y = rand.nextDouble() * 500 + 10;
            list.add(new City(name, new Location(x, y)));
        }
        return list;
    }

    private static List<Monster> generateMonsters(int count) {
        List<Monster> list = new ArrayList<>();
        String[] types = {"Грифон", "Утопец", "Гуль", "Брукса", "Леший", "Циклоп", "Виверна", "Полуденница"};
        Random rand = new Random(24);

        for (int i = 1; i <= count; i++) {
            String name = types[rand.nextInt(types.length)] + " в логове #" + i;
            double x = rand.nextDouble() * 600 - 50;
            double y = rand.nextDouble() * 600 - 50;
            list.add(new Monster(name, new Location(x, y)));
        }
        return list;
    }
}
