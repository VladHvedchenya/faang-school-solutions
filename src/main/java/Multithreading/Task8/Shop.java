package Multithreading.Task8;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Shop {
    private static final int TOTAL_CASHIERS = 3;

    public static void main(String[] args){
        List<List<Item>> buyers = List.of(
                List.of(new Item("Молоко", 90), new Item("Хлеб", 45)),
                List.of(new Item("Шоколад", 120), new Item("Кофе", 450), new Item("Яблоки", 150)),
                List.of(new Item("Стиральный порошок", 600)),
                List.of(new Item("Вода", 35), new Item("Печенье", 85)),
                List.of(new Item("Сыр", 300), new Item("Колбаса", 400))
        );

        List<CashierThread> threads = new ArrayList<>(buyers.size());

        for (var buyer : buyers){
            int randomCashierId = ThreadLocalRandom.current().nextInt(1, TOTAL_CASHIERS + 1);
            CashierThread thread = new CashierThread(randomCashierId, buyer);
            threads.add(thread);
            thread.start();
        }

        for (CashierThread thread : threads){
            try{
                thread.join();
            }
            catch (InterruptedException e){
                Thread.currentThread().interrupt();
                System.out.println("Поток был прерван");
            }
        }
    }
}