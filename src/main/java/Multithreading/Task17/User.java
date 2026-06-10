package Multithreading.Task17;

import lombok.Getter;

public class User {
    @Getter
    private final String name;
    @Getter
    private final Role role;
    private House house;

    public User(String name, Role role) {
        if (name == null)
            throw new NullPointerException("The name can't ne null");
        if (name.isEmpty())
            throw new IllegalArgumentException("The name can't be empty");
        this.name = name;
        this.role = role;
    }

    public void joinHouse(House house) {
        this.house = house;
        Thread.currentThread().setName(name);
        house.assignRole(this);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }

        leaveHouse();
    }

    private void leaveHouse() {
        if (house != null){
            house.releaseRole(this);
            house = null;
        }
    }
}