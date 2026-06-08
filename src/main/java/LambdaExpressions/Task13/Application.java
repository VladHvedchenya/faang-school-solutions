package LambdaExpressions.Task13;

public class Application {
    void main(){
        TriangleAreaCalculator calculator = new TriangleAreaCalculator();
        double area = calculator.calculateTriangleArea(3,4,5);
        System.out.println("Площадь треугольника: " + area);
    }
}