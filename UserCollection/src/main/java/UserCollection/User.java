package UserCollection;

import java.util.Collections;
import java.util.Set;

public class User {
    private final int id;
    private final String name;
    private final int age;
    private Set<String> activities;

    private static int counter = 0;

    public User(String name, int age, Set<String> activities){
        if (name == null)
            throw new NullPointerException("The name can't be null");
        if (name.isBlank())
            throw new IllegalArgumentException("The name can't be empty!");
        if (age < 0 || age > 120)
            throw new IllegalArgumentException("The age should be real.");
        this.id = ++counter;
        this.name = name;
        this.age = age;
        this.activities = activities;
    }

    public Set<String> getActivities(){
        return Collections.unmodifiableSet(activities);
    }
}