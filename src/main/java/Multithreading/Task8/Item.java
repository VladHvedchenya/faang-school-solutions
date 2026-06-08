package Multithreading.Task8;

public record Item(String name, int price) {
    public Item {
        if (price < 0) {
            throw new IllegalArgumentException("Цена товара не может быть отрицательной");
        }
    }
}