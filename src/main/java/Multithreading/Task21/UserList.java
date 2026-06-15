package Multithreading.Task21;

import java.util.HashSet;
import java.util.Set;

public class UserList {
    private final Set<User> users = new HashSet<>();

    public void addUser(User user) {
        if (user == null) throw new NullPointerException("The user can't be null");
        users.add(user);
    }

    public Set<User> getAllUsers() {
        return users;
    }
}
