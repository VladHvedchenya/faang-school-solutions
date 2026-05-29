package com.LambdaExpressions.Task5;

public class Application {
    static void main() {
        Character character = new Character("Frodo");
        character.addItem(new Item("Кольцо", 200));
        character.addItem(new Item("Кольчуга", 300));
        character.addItem(new Item("Поножи", 140));
        character.addItem(new Item("Тапки", 100));

        InventoryManager manager = new InventoryManager();
        manager.addItem(character, new Item("Шапка", 80), (item) -> System.out.println("Добавлен предмет!" + item.getName()));
        manager.deleteItem(item -> item.getName().equals("Поножи"), character);
        manager.updateItem(character, item -> item.getName().equals("Кольчуга"), item -> new Item(item.getName(), item.getValue() * 2));
        character.showInventory();
    }
}