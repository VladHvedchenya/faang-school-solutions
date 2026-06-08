package LambdaExpressions.Task9;

import java.util.ArrayList;
import java.util.List;

public class Application {
    void main(){
        List<MessageFilter> filters = new ArrayList<>();
        filters.add(
                (string -> !string.toLowerCase().contains("spam"))
        );
        filters.add(
                string -> string.length() > 10
        );
        filters.add(
                string -> !string.isBlank()
        );

        MessageProcessor processor = new MessageProcessor();
        System.out.println(processor.processMessage("lalala bla bla bla spam", filters)
                ? "Сообщение прошло все фильтры" : "Сообщение не прошло фильтрацию");
    }
}