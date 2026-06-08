package Multithreading.Task10;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

public class CityWorker implements Callable<Map<Monster, Double>> {
    private final City city;
    private final List<Monster> monsters;
    private final double distanceFromCastle;

    public CityWorker(City city, List<Monster> monsters, double distanceFromCastle){
        if(city == null)
            throw new NullPointerException("The city can't be null");
        if(monsters == null)
            throw new NullPointerException("The list can't be null");
        this.city = city;
        this.monsters = monsters;
        this.distanceFromCastle = distanceFromCastle;
    }

    @Override
    public Map<Monster, Double> call() {
        Map<Monster, Double> distance = new HashMap<>(monsters.size());

        for (var monster : monsters){
            double cityToMonster = city.location().distanceTo(monster.location());
            double totalPath = distanceFromCastle + cityToMonster;
            distance.put(monster, totalPath);
        }

        return distance;
    }
}