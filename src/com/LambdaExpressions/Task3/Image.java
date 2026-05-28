package com.LambdaExpressions.Task3;

public class Image {
    private final String name;
    private final String description;

    public Image(String name, String description){
        //for sure here will be null-checks and empty-checks for String type.
        this.name = name;
        this.description = description;
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    @Override
    public String toString() {
        return "Название картинки " + name + ". Описание картинки " + description;
    }
}