package Multithreading.Task9;

public class NufNufThread extends PigThread{
    public NufNufThread(String name, Material material) {
        super(name, material);
    }

    @Override
    protected int getTimeMillis() {
        return 500;
    }
}