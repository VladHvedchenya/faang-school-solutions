package Multithreading.Task9;

public class NafNafThread extends PigThread{
    public NafNafThread(String name, Material material) {
        super(name, material);
    }

    @Override
    protected int getTimeMillis() {
        return 300;
    }
}