package Multithreading.Task23;

import lombok.Data;

import java.util.concurrent.ThreadLocalRandom;

@Data
public class Quest {
    private final String title;
    private final int difficulty;
    private final int reward;

    public Quest(String title){
        //null & empty checks for string type
        this.title = title;
        this.difficulty = ThreadLocalRandom.current().nextInt(0, 10);
        this.reward = ThreadLocalRandom.current().nextInt(10, 100);
    }
}