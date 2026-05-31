package com.LambdaExpressions.Task15;

public class Coordinates {
    private final int rowIndex;
    private final int columnIndex;

    public Coordinates(int row, int column){
        this.rowIndex = row;
        this.columnIndex = column;
    }

    public int getRowIndex(){
        return rowIndex;
    }

    public int getColumnIndex(){
        return columnIndex;
    }
}