package Multithreading.Task19;

import lombok.Getter;

@Getter
public class Product {
    private final FoodType type;
    
    public Product(FoodType type){
        this.type = type;
    }
}