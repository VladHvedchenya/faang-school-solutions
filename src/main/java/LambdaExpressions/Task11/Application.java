package LambdaExpressions.Task11;

import java.util.List;

public class Application {
    void main(){
        Calculator<Integer> calculateSum = (a, b) -> a + b;
        Calculator<Integer> calculateProduct = (a, b) -> a * b;

        List<Integer> arr = List.of(1,2,3,4);
        System.out.println("Сумма: " + calculate(arr, calculateSum));
        System.out.println("Произведение: " + calculate(arr, calculateProduct));
    }

    public int calculate(List<Integer> values, Calculator<Integer>calculator){
        //list null & empty checks
        int res = values.getFirst();

        for (int i = 1; i < values.size(); i++) {
            res = calculator.calculate(res, values.get(i));
        }

        return res;
    }
}