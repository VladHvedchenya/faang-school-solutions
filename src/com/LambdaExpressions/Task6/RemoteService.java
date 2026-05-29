package com.LambdaExpressions.Task6;

public class RemoteService {
    public String call(String param){
        if (param.isBlank())
            throw new IllegalArgumentException("Параметр пустой.");
        return "Доступ получен";
    }
}