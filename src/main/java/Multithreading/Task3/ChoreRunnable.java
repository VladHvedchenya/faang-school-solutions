package Multithreading.Task3;

public class ChoreRunnable implements Runnable{
    private final Chore chore;

    public ChoreRunnable(Chore chore){
        //null & empty checks
        this.chore = chore;
    }

    @Override
    public void run() {
        chore.execute();
    }
}