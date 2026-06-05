package com.GroupingTask;

public class User {
    private final String name;
    private final int age;
    private final String workplace;
    private final String address;

    public User(String name, int age, String workplace, String address){
        if (name == null)
            throw new NullPointerException("The name shouldn't be null");
        if (name.isBlank())
            throw new IllegalArgumentException("The name can't be empty or contain only spaces");
        if (age < 0 || age > 100)
            throw new IllegalArgumentException("The age is invalid!");
        this.name = name;
        this.age = age;
        this.workplace = workplace;
        this.address = address;
    }

    public int getAge(){
        return age;
    }

    @Override
    public String toString() {
        return "Имя: " + name + " возраст " + age + " место работы " + workplace + " адрес " + address;
    }
}