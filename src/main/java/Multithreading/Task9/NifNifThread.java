package Multithreading.Task9;

public class NifNifThread extends PigThread{
    public NifNifThread(String name, Material material) {
        super(name, material);
    }

    @Override
    protected int getTimeMillis() {
        return 100;
    }
}