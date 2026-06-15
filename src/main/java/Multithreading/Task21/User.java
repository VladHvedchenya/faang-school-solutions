package Multithreading.Task21;

import lombok.Setter;

public class User {
    private final String name;
    private boolean isOnline = true;
    @Setter
    private boolean isLookingForChat = true;

    public User(String name) {
        if (name == null) throw new NullPointerException("The name can't be null");
        if (name.isEmpty()) throw new IllegalArgumentException("The name can't be empty");
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public boolean isLookingForChat() {
        return isLookingForChat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return name.equals(user.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return name;
    }
}
