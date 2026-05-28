package com.LambdaExpressions.Task4;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Application {
    void main(){
        List<Email> emails = List.of(
                new Email("История", "Столетняя война продолжалась 116 лет", true),
                new Email("География", "Марианская впадика - самое глубокое место в мире", false),
                new Email("Математика", "Изучение арифметики, алгоритмов, геометрии и алгебры", true)
        );

        Predicate<Email> filter = email -> email.isImportant();
        Function<Email, String> transformer = email -> email.getBody() + " | Google INC." + email.isImportant();
        Consumer<Email> action = email -> System.out.println("Тема: " + email.getSubject() + ". Сущность письма: " + email.getBody());

        EmailProcessor processor = new EmailProcessor();
        processor.processEmails(emails, filter, transformer, action);
    }
}