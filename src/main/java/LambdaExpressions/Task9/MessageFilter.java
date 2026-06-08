package LambdaExpressions.Task9;

@FunctionalInterface
public interface MessageFilter {
    boolean filter(String string);
}