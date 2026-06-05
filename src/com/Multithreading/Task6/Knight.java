package com.Multithreading.Task6;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Knight {
    private final String name;
    private List<Trial> trials;

    public Knight(String name){
        //null & empty checks for String and List types
        this.name = name;
        this.trials = new ArrayList<>();
    }

    public void addTrial(Trial trial){
        if(trial == null)
            throw new NullPointerException("Trial is null!");

        trials.add(trial);
    }

    public void startTrials(ExecutorService executor){
        trials.forEach(trial -> executor.submit(() -> trial.execute(name)));
    }
}