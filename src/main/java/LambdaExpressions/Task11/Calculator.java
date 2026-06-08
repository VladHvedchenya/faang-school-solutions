package LambdaExpressions.Task11;

@FunctionalInterface
public interface Calculator<T> {
     T calculate(T t1, T t2);
}