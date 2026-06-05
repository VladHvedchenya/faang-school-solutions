package com.Multithreading.Task2;

import com.Multithreading.Task2.Units.Unit;

import java.util.List;

public class Squad <T extends Unit>{
    private final List<T> squad;

    public Squad(List<T> squad){
        //null & empty checks for list
        this.squad = squad;
    }

    public int calculateSquadPower(){
        return squad.stream().mapToInt(Unit::getPower).sum();
    }
}