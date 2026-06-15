package Multithreading.Task23;

import lombok.Data;

@Data
public class Player {
    private final String name;
    private int level = 0;
    private int experience = 0;

    public Player(String name){
        //String null & empty checks
        this.name = name;
    }
}
