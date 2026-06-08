package Multithreading.Task2;

public class SquadPowerCalculator extends Thread {
    private final Squad squad;
    private int totalPower;

    public SquadPowerCalculator(Squad squad){
        //null and empty checks
        this.squad = squad;
    }

    @Override
    public void run() {
        totalPower += squad.calculateSquadPower();
    }

    public int getTotalPower(){
        return totalPower;
    }
}