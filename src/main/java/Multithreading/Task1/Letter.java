package Multithreading.Task1;

public class Letter {
    private final String name;

    public Letter(String name){
        //null & empty checks
        this.name = name;
    }

    public String getName(){
        return name;
    }
}