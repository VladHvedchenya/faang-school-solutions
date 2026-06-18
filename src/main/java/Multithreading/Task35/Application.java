package Multithreading.Task35;

public class Application {
    public static void main(String[] args){
        PICalculator calculator = new PICalculator();
        System.out.println(calculator.calculatePI(100_000_000_000L));
    }
}
