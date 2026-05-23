import groupingtask.GroupingUtil;
import groupingtask.User;

void main() {
    List<User> users = List.of(
            new User("Vlad", 21, "TBank", "Minsk"),
            new User("Dmitry", 25, "Sber", "Minsk"),
            new User("Alex", 21, "Uber", "Amsterdam"),
            new User("Mariya", 25, "Google", "Warsaw"),
            new User("Polina", 21, "Netflix", "New York"));

    for (Map.Entry<Integer, List<User>> entry : GroupingUtil.groupUsers(users, User::getAge).entrySet()) {
        IO.println("Возраст пользователей: " + entry.getKey());

        for (User user : entry.getValue()) {
            IO.println(user.toString());
        }
    }
}