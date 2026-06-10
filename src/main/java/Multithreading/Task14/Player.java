package Multithreading.Task14;

public class Player {
    private final String name;

    public Player(String name) {
        if (name == null)
            throw new NullPointerException("The name can't be null");
        if (name.isEmpty()) {
            throw new IllegalArgumentException("The name can't be empty");
        }

        this.name = name;
    }

    public String getName(){
        return name;
    }
}
