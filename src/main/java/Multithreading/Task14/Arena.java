package Multithreading.Task14;

import java.util.ArrayList;
import java.util.List;

public class Arena {
    public static void main(String[] args){
        Boss boss = new Boss(3, new ArrayList<>());
        Thread addThread = new Thread(boss::tryConnectToBattle);
        Thread leaveThread = new Thread(boss::leaveBattle);
        addThread.start();
        leaveThread.start();
    }
}
