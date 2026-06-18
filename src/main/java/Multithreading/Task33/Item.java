package Multithreading.Task33;

public record Item(String name, int power) {
    public Item{
        if (name == null)
            throw new NullPointerException("The name can't be null");
        if (name.isBlank())
            throw new IllegalArgumentException("The name can't be empty");
        if (power < 0)
            throw new IllegalArgumentException("The power should be greater or equals than 0");
    }
}