package LambdaExpressions.Task10;

import java.util.function.BiFunction;

public class FareCalculator {
    public Double calculateFare(double distance, double time, BiFunction<Double, Double, Double> calculatePrice){
        return calculatePrice.apply(distance, time);
    }
}