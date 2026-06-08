package Multithreading.Task7;

public class Person {
    private final String name;
    private final String surname;
    private final String workplace;
    private final int age;

    public Person(String name, String surname, String workplace, int age){
        //null & empty checks for String type
        //int checks for legal age
        this.name = name;
        this.surname = surname;
        this.workplace = workplace;
        this.age = age;
    }

    @Override
    public String toString() {
        return name + " " + surname + " " + workplace + " " + age;
    }
}