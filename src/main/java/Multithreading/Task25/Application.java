package Multithreading.Task25;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

public class Application {
    private static final AtomicInteger sum = new AtomicInteger(0);

    public static void main(String[] args) {
        List<Potion> potions = List.of(
                new Potion("Зелье лечения (Восстановление здоровья)", 6),
                new Potion("Яд паралича (Паралич противника)", 4),
                new Potion("Зелье невидимости (Скрытие в тенях)", 8),
                new Potion("Зелье магии (Восстановление маны)", 3),
                new Potion("Зелье сопротивления огню (Защита от драконов)", 5)
        );

        CompletableFuture<?>[] tasks = potions.stream()
                .map(potion -> CompletableFuture.runAsync(() -> collectIngredients(potion)))
                .toArray(CompletableFuture[]::new);
        CompletableFuture.allOf(tasks).join();
        System.out.printf("Общее количество ингридиентов: %s", sum.get());
    }

    private static void collectIngredients(Potion potion) {
        try {
            System.out.printf("🌿 [%s] Взял %s и собираю ингредиенты...\n", Thread.currentThread().getName(), potion.name());
            Thread.sleep(potion.ingredientCount() * 500L);
            sum.addAndGet(potion.ingredientCount());
            System.out.printf("✅ [%s] Собрал %d шт. для %s\n", Thread.currentThread().getName(), potion.ingredientCount(), potion.name());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException();
        }
    }
}