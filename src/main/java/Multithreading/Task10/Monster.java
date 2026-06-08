package Multithreading.Task10;

public record Monster(String name, Location location) {
    public Monster {
        if (name == null)
            throw new NullPointerException("The name can't be null");
        if (location == null)
            throw new NullPointerException("The location can't be null");
        if (name.isEmpty())
            throw new NullPointerException("The name can't be empty");
    }
}