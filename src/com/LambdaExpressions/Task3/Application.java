package com.LambdaExpressions.Task3;

import org.w3c.dom.ls.LSOutput;

import java.util.List;
import java.util.function.Function;

public class Application {
    void main(){
        FilterProcessor processor = new FilterProcessor();
        List<String> imageTypes = List.of("Сепия", "Черно-белый", "Виньетка", "Bright");
        Image image = new Image("Собака", "Красивая собака, которая бежит");

        List<Function<Image, Image>> filters = List.of(
                n -> new Image(n.getName(), n.getDescription() + " Фильтр: Сепия"),
                n -> new Image(n.getName(), n.getDescription() + " Фильтр: Яркий"),
                n -> new Image(n.getName(), n.getDescription() + " Фильтр: Черно-белый"),
                n -> new Image(n.getName(), n.getDescription() + " Фильтр: Виньетка")
        );

        System.out.println("Одиночный фильтр: ");
        System.out.println(processor.applyFilter(image, filters.getFirst()).getDescription());
        System.out.println("Комбинированный фильтр: ");

        Function<Image, Image> combined = processor.combineFilters(filters.get(1), filters.get(3));

        System.out.println(processor.applyFilter(image, combined));
    }
}