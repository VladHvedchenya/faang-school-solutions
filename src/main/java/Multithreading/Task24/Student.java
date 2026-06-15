package Multithreading.Task24;

import lombok.Data;

@Data
public class Student {
    private final String name;
    private final int year;
    private int points = 0;

    public Student(String name, int year) {
        //string and year checks
        this.name = name;
        this.year = year;
    }
}