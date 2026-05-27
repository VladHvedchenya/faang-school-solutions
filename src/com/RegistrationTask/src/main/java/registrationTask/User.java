package registrationTask;

import java.util.Objects;

public class User {
    private final String name;
    private final int age;
    private final ConstantUtil.Jobs job;
    private final ConstantUtil.Addresses address;

    public User(String name, int age, ConstantUtil.Jobs job, ConstantUtil.Addresses address){
        if (name == null)
            throw new NullPointerException("The name can't be null");
        if (name.isBlank())
            throw new IllegalArgumentException("The name can't be empty");
        if (age < ConstantUtil.MIN_AGE)
            throw new IllegalArgumentException("The illegal age! You are too young!");

        this.name = name;
        this.age = age;
        this.job = Objects.requireNonNull(job, "job cannot be null");
        this.address = Objects.requireNonNull(address, "address cannot be null");;
    }
}
