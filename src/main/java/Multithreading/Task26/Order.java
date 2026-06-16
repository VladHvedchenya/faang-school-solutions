package Multithreading.Task26;

import lombok.Getter;
import lombok.Setter;

public class Order {
    @Getter
    private final int id;
    @Setter
    private boolean isProcessed;

    public Order(int id) {
        this.id = id;
    }

    public String getStatus(){
        return isProcessed ? "Обработан" : "Не обработан";
    }
}