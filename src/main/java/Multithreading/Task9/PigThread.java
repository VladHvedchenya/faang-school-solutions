package Multithreading.Task9;

public abstract class PigThread extends Thread {
    private final String name;
    private final Material material;

    public PigThread(String name, Material material){
        //null & empty checks for String type
        this.name = name;
        this.material = material;
    }

    @Override
    public final void run() {
        System.out.println(name + " начал строить дом.");
        System.out.println(name + " укладывает материал: " + material);

        try{
            Thread.sleep(getTimeMillis());
        }
        catch (InterruptedException e){
            Thread.currentThread().interrupt();
            return;
        }

        System.out.println("=== " + name + " закончил дом из материала: " + material + " ===");
    }

    protected abstract int getTimeMillis();

    public Material getMaterial() {
        return material;
    }

    public String getPigName() {
        return name;
    }
}