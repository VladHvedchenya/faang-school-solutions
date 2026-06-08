package Multithreading.Task2;

import java.util.ArrayList;
import java.util.List;

public class Army {
    private final List<Squad> squads;

    public Army(List<Squad> squads) {
        //null & empty checks for list
        this.squads = squads;
    }

    public int calculateTotalPower() {
        List<SquadPowerCalculator> tasks = new ArrayList<>();
        int totalArmyPower = 0;

        for (var squad : squads) {
            SquadPowerCalculator task = new SquadPowerCalculator(squad);
            tasks.add(task);
            task.start();
        }

        try{
            for (var task : tasks){
                task.join();
                totalArmyPower += task.getTotalPower();
            }
        }
        catch (InterruptedException e){
            System.err.println("Расчет общей силы армии был прерван!");
            Thread.currentThread().interrupt();
        }

        return totalArmyPower;
    }
}