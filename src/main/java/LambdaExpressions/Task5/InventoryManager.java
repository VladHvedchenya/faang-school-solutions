package LambdaExpressions.Task5;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class InventoryManager {
    public void addItem(Character character, Item item, Consumer<Item> action){
        character.addItem(item);
        action.accept(item);
    }

    public void deleteItem(Predicate<Item> filter, Character character){
        character.deleteItem(filter);
    }

    public void updateItem(Character character, Predicate<Item> filter, Function<Item, Item> transformer){
        character.updateItem(filter, transformer);
    }
}