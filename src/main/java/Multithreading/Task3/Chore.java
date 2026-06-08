package Multithreading.Task3;

public class Chore {
    private final String name;

    public Chore(String name) {
        //null & empty checks for string type!
        this.name = name;
    }

    public void execute() {
        System.out.println(Thread.currentThread().getName() + " НАЧАЛ выполнять задачу: " + name);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.err.println("Выполнение задачи " + name + " было прервано!");
            Thread.currentThread().interrupt();
        }

        System.out.println(Thread.currentThread().getName() + " ЗАВЕРШИЛ задачу: " + name);
    }

    public String getName() {
        return name;
    }
}