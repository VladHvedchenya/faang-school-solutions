package com.LambdaExpressions.Task4;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class EmailProcessor {
    public void processEmails(List<Email> emails, Predicate<Email> filter, Function<Email, String> transformer, Consumer<Email> action){
        emails.forEach(
                email -> {
                    if (filter.test(email)){
                        email.setBody(transformer.apply(email));
                        action.accept(email);
                    }
                }
        );
    }
}