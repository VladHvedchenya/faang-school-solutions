package LambdaExpressions.Task15;

import java.util.NoSuchElementException;

public class Application {
    void main(){
        int[][] matrix = {
                {1,2,3,4},
                {5,6,7,8},
                {9,10,11,12},
                {13,14,15,16}
        };
        int[][] horizontal = flipMatrix(matrix, FlipDirection.HORIZONTAL);

        for (int i = 0; i < horizontal.length; i++) {
            for (int j = 0; j < horizontal[0].length; j++) {
                System.out.print(horizontal[i][j]);
            }
            System.out.println();
        }

        int[][] vertical = flipMatrix(matrix, FlipDirection.VERTICAL);
        for (int i = 0; i < vertical.length; i++) {
            for (int j = 0; j < vertical[0].length; j++) {
                System.out.print(vertical[i][j]);
            }
            System.out.println();
        }
    }

    static int[][] transformMatrix(int[][] matrix, MatrixTransformer transformer){
        //matrix null and empty checks!
        int[][] flippedMatrix = new int[matrix.length][matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                Coordinates updatedCoordinated = transformer.transform(i, j);
                flippedMatrix[updatedCoordinated.getRowIndex()][updatedCoordinated.getColumnIndex()] = matrix[i][j];
            }
        }

        return flippedMatrix;
    }

    static int[][] flipMatrix(int[][] matrix, FlipDirection direction){
        if (direction == FlipDirection.HORIZONTAL){
            MatrixTransformer horizontalTransformer = (i, j) -> new Coordinates(i, matrix.length - 1 - j);

            return transformMatrix(matrix, horizontalTransformer);
        }
        else if (direction == FlipDirection.VERTICAL){
            MatrixTransformer verticalTransformer = (i, j) -> new Coordinates(matrix[0].length - 1 - i, j);

            return transformMatrix(matrix, verticalTransformer);
        }
        else{
            throw new NoSuchElementException("There is no such direction!");
        }
    }
}