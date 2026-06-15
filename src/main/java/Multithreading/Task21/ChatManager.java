package Multithreading.Task21;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ChatManager {
    private final UserList list = new UserList();
    private final Queue<User> waiting = new LinkedList<>();

    public ChatManager() {
        list.addUser(new User("Vlad"));
        list.addUser(new User("Stepan"));
        list.addUser(new User("Mariya"));
        list.addUser(new User("Gleb"));
        list.addUser(new User("Bogdan"));
        list.addUser(new User("Leya"));
        list.addUser(new User("Polina"));
    }

    public List<User> getInitialUsers() {
        return new ArrayList<>(list.getAllUsers());
    }

    public void startChat(User user) {
        Chat chat = null;

        synchronized (waiting) {
            if (!user.isOnline() || !user.isLookingForChat()) {
                return;
            }

            if (!waiting.isEmpty()) {
                User partner = waiting.poll();

                chat = new Chat(user, partner);

                System.out.println(String.format("❤️ МЭТЧ! [%s] зашел и создал чат с [%s]. В очереди осталось: %d",
                        user, partner, waiting.size()));

                waiting.notifyAll();

            } else {
                waiting.add(user);
                System.out.println(String.format("⏳ [%s] никого не нашел и встал в очередь. Всего ждут: %d",
                        user, waiting.size()));

                while (user.isLookingForChat()) {
                    try {
                        waiting.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        waiting.remove(user);
                        return;
                    }
                }

                return;
            }
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            synchronized (waiting) {
                chat.end();
                System.out.println(String.format("💔 Чат завершен между [%s] и [%s]. Они вернулись в поиск.",
                        chat.getFirst(), chat.getSecond()));

                waiting.notifyAll();
            }
        }
    }
}
