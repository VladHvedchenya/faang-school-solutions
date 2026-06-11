package Multithreading.Task18;

public class TamagochiVlad {
    private int hunger = 100;
    private int energy = 100;
    private int dirtyLevel = 0;

    private static final int MAX_STAT = 100;
    private static final int MIN_STAT = 0;

    private final String name;

    private final Thread timeThread;

    public TamagochiVlad(String name) {
        if (name == null)
            throw new NullPointerException("The name can't be null");
        if (name.isEmpty())
            throw new IllegalArgumentException("The name can't be empty");
        this.name = name;
        timeThread = new Thread(this::live);
        timeThread.start();
    }

    private final Object lock = new Object();


    public void feed() {
        synchronized (lock) {
            while (hunger > 60) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            hunger = MAX_STAT;
            System.out.printf("🍗 [%s] Влад %s вкусно поел! Семья счастлива. (Сытость: %d)%n",
                    Thread.currentThread().getName(), name, hunger);
        }
    }

    public void play() {
        synchronized (lock) {
            while (energy < 40) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            energy = Math.max(MIN_STAT, energy - 20);
            System.out.printf("🎮 [%s] Поиграли с Владом %s в Supercow! Стресс снят. (Энергия упала до: %d)%n",
                    Thread.currentThread().getName(), name, energy);
        }
    }

    public void clean() {
        synchronized (lock) {
            while (dirtyLevel < 40) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            dirtyLevel = MIN_STAT;
            System.out.printf("🧼 [%s] Влад %s отмыт до блеска! Прощай, грязь. (Уровень грязи: %d)%n",
                    Thread.currentThread().getName(), name, dirtyLevel);
        }
    }

    public void sleep() {
        synchronized (lock) {
            if (energy > 30) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            energy = MAX_STAT;
            System.out.printf("💤 [%s] Влад %s сладко поспал и восстановил силы! (Энергия: %d)%n",
                    Thread.currentThread().getName(), name, energy);
        }
    }

    private void live(){
        while(!timeThread.isInterrupted()){
            try{
                Thread.sleep(3000);
            }
            catch (InterruptedException e){
                Thread.currentThread().interrupt();
                return;
            }

            synchronized (lock){
                hunger = Math.max(MIN_STAT, hunger - 10);
                energy = Math.max(MIN_STAT, energy - 20);
                dirtyLevel = Math.min(MAX_STAT, dirtyLevel + 10);
                System.out.printf("%n⏰ [Тик-Так] Состояние %s изменилось! Сытость: %d, Энергия: %d, Грязь: %d%n",
                        name, hunger, energy, dirtyLevel);
                lock.notifyAll();
            }
        }
    }
}