package LambdaExpressions.Task13;

import java.util.function.Function;

public class TriangleAreaCalculator {
    private final Function<Double, Function<Double, Double>> add = (x) -> (y) -> x + y;
    private final Function<Double, Function<Double, Double>> multiply = (x) -> (y) -> x * y;
    private final Function<Double, Function<Double, Double>> subtract = (x)-> (y) -> x - y;
    private final Function<Double, Function<Double, Double>> divide = (x) -> (y) -> x / y;
    private final Function<Double, Double> square_root = Math :: sqrt;

    public Double calculateTriangleArea(double a,double b,double c){
        if (add.apply(a).apply(b) <= c || add.apply(a).apply(c) <= b || add.apply(b).apply(c) <= a)
            throw new IllegalArgumentException("The triangle with this variables can't exist!");

        double semiPerimeter = divide.apply(add.apply(add.apply(a).apply(b)).apply(c)).apply(2.0);
        return square_root.apply(multiply.apply(semiPerimeter).apply(multiply.apply(subtract.apply(semiPerimeter).apply(a)).apply(
                multiply.apply(subtract.apply(semiPerimeter).apply(b)).apply(subtract.apply(semiPerimeter).apply(c))
        )));
    }
}