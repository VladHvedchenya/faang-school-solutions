package Multithreading.Task17;

import java.util.ArrayList;
import java.util.List;

public class House {
    private List<Role> roles;
    private List<User> users;
    private final Object lock = new Object();

    public House() {
        users = new ArrayList<>();
        roles = new ArrayList<>();
        roles.add(Role.DRAWF);
        roles.add(Role.DRAWF);
        roles.add(Role.ARCHE);
        roles.add(Role.KNIGHT);
        roles.add(Role.TARGARIAN);
        roles.add(Role.TARGARIAN);
    }

    public void assignRole(User user) {
        Role userRole = user.getRole();

        synchronized (lock) {
            while (!roles.contains(userRole)) {
                try {
                    System.out.printf("[ОЧЕРЕДЬ] ⏳ Игрок %s хочет роль %s, но она ЗАНЯТА. Ждем...%n",
                            user.getName(), userRole);
                    lock.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("Поток был прерван");
                    return;
                }
            }

            users.add(user);
            roles.remove(userRole);

            System.out.printf("⚔️ [ЗАМОК] Игрок %s УСПЕШНО занял роль %s! (Всего в замке: %d)%n",
                    user.getName(), userRole, users.size());
        }
    }

    public void releaseRole(User user) {
        Role userRole = user.getRole();

        synchronized (lock) {
            users.remove(user);
            roles.add(userRole);
            System.out.printf("   ❌ [ВЫХОД] Игрок %s покинул роль %s. Слот снова свободен!%n",
                    user.getName(), userRole);
            lock.notifyAll();
        }
    }
}