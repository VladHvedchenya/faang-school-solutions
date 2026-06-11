package Multithreading.Task19;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Room {
    private final List<Product> products = new ArrayList<>();

    public Room(){
        generateProducts();
    }

    public List<Product> collectProducts(){
        List<Product> collected = new ArrayList<>(products);
        products.clear();
        return collected;
    }

    public boolean isEmpty(){
        return products.isEmpty();
    }

    private void generateProducts(){
        int randomCount = ThreadLocalRandom.current().nextInt(0, 10);

        for (int i = 0; i < randomCount; i++) {
            int randomIndex = ThreadLocalRandom.current().nextInt(0, FoodType.values().length);
            products.add(new Product(FoodType.values()[randomIndex]));
        }
    }
}