package com.LambdaExpressions.Task14;

import java.util.List;

public class Application {
    void main(){
        CVSJoiner joiner = new CVSJoiner();

        List<List<String>> matrix = List.of(
                List.of("1","2","3","4","5"),
                List.of("6","7","8","9","10"),
                List.of("11","12","13","14","15"),
                List.of("16","17","18","19","20"),
                List.of("21","22","23","24","25")
        );

        List<List<Integer>> values = List.of(
                List.of(1,2,3,4,5),
                List.of(6,7,8,9,10),
                List.of(11,12,13,14,15),
                List.of(16,17,18,19,20),
                List.of(21,22,23,24,25)
        );

        System.out.println(joiner.toCSV(matrix));
        System.out.println(joiner.toCSV(values));
    }
}