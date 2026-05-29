package com.LambdaExpressions.Task6;

@FunctionalInterface
public interface ExceptionHandler<T> {
    T handleError(Exception e);
}