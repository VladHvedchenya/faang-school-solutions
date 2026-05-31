package com.LambdaExpressions.Task15;

@FunctionalInterface
public interface MatrixTransformer {
    Coordinates transform(int rowIndex, int columnIndex);
}