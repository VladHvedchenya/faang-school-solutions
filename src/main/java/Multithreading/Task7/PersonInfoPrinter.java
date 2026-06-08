package Multithreading.Task7;

import java.util.List;

public class PersonInfoPrinter implements Runnable {
    private final List<Person> group;

    public PersonInfoPrinter(List<Person> group){
        //null & empty checks for List type
        this.group = group;
    }

    @Override
    public void run() {
        group.forEach(person -> System.out.println(person.toString()));
    }
}