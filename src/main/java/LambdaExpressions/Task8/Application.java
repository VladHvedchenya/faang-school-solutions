package LambdaExpressions.Task8;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class Application {
    void main(){
        Map<String, String> dic = new HashMap<>();
        DictionaryProcessor processor = new DictionaryProcessor();

        BiConsumer<String, String> action = (w, t) -> {
            dic.put(w, t);
        };
        processor.processWord("Hello", "Привет", action);
        processor.processWord("World", "Мир", action);
        processor.processWord("Home", "Дом", action);
        processor.processWord("Computer", "Компьютер", action);

        System.out.println(dic);
    }
}