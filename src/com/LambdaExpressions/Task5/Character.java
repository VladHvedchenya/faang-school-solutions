package com.LambdaExpressions.Task5;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class Character {
    private final String name;
    private final List<Item> inventory;

    public Character(String name){
        //String null & empty checks. List null checks
        this.name = name;
        this.inventory = new ArrayList<>();
    }

    public void addItem(Item item){
        inventory.add(item);
    }

    public void deleteItem(Predicate<Item> filter){
        inventory.removeIf(filter);
    }

    public void updateItem(Predicate<Item> predicate, Function<Item, Item> updater) {
        for (int i = 0; i < inventory.size(); i++) {
            if (predicate.test(inventory.get(i))) {
                inventory.set(i, updater.apply(inventory.get(i)));
            }
        }
    }

    public void showInventory(){
        inventory.forEach(item -> System.out.println("Предмет: " + item.getName() + "Стоимость: " + item.getValue()));
    }
}