package com.LambdaExpressions.Task12;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class LocationSearchEngine {
    public List<Location> filterLocations(List<Location> locations, Predicate<Location> isCorrect){
        //null list checks provided
        List<Location> correctLocations = new ArrayList<>();

        for (var location : locations){
            if (isCorrect.test(location))
                correctLocations.add(location);
        }

        return correctLocations;
    }

    public void processLocations(List<Location> locations, Consumer<Location> processor){
        //null list checks provided
        for (var location : locations){
            processor.accept(location);
        }
    }

    public List<Double> calculateDistance(List<Location> locations, Function<Location, Double> calculator){
        //null list checks provided
        List<Double> res = new ArrayList<>(locations.size());

        for (var location : locations){
            res.add(calculator.apply(location));
        }

        return res;
    }
}