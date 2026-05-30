package com.LambdaExpressions.Task10;

import java.util.function.BiFunction;

public class Application {
    void main(){
        FareCalculator calculator = new FareCalculator();

        BiFunction<Double, Double, Double> calculateBasicFare = (distance, time) -> distance * 1.0 + time * 1.0;
        BiFunction<Double, Double, Double> calculateComfortFare = (distance, time) -> distance * 1.5 + time * 1.5;
        BiFunction<Double, Double, Double> calculateBusinessFare = (distance, time) -> distance * 2.5 + time * 2.5;

        System.out.println("Цена обычного такси: " + calculator.calculateFare(10, 5, calculateBasicFare));
        System.out.println("Цена обычного такси: " + calculator.calculateFare(10, 5, calculateComfortFare));
        System.out.println("Цена обычного такси: " + calculator.calculateFare(10, 5, calculateBusinessFare));
    }
}