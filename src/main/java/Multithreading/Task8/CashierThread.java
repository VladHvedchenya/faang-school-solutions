package Multithreading.Task8;

import java.util.List;

public class CashierThread extends Thread {
    private final int cashierId;
    private final List<Item> buyer;

    public CashierThread(int cashierId, List<Item> buyer){
        //check for legal int and null & empty checks for list
        this.cashierId = cashierId;
        this.buyer = List.copyOf(buyer);
    }

    @Override
    public void run() {
        for (Item item : buyer) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.printf("[Касса %d] Обслуживание прервано во время сканирования.%n", cashierId);
                return;
            }
        }

        int total = calculateSum(buyer);

        System.out.printf("[Касса %d] Обслуживание завершено. Сумма: %d руб.%n",
                cashierId, total);
    }

    private int calculateSum(List<Item> items) {
        return items.stream()
                .mapToInt(Item::price)
                .sum();
    }
}