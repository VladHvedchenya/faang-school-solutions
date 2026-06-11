package Multithreading.Task18;

import java.util.List;
import java.util.concurrent.ExecutorService;

public class VladController {
    private List<TamagochiVlad> vlads;

    public VladController(List<TamagochiVlad> vlads) {
        if (vlads == null)
            throw new NullPointerException("List can't be null");
        if (vlads.isEmpty())
            throw new IllegalArgumentException("The list is empty, add tamagochi.");
        this.vlads = vlads;
    }

    public void start(ExecutorService executor) {
        executor.submit(() -> {
            Thread.currentThread().setName("Поток-кормилец");

            while (!Thread.currentThread().isInterrupted()){
                feedAll();
            }
        });

        executor.submit(() -> {
            Thread.currentThread().setName("Поток-Аниматор");

            while (!Thread.currentThread().isInterrupted()){
                playAll();
            }
        });

        executor.submit(() -> {
            Thread.currentThread().setName("Поток-клинер");

            while (!Thread.currentThread().isInterrupted()){
                cleanAll();
            }
        });

        executor.submit(() -> {
            Thread.currentThread().setName("Поток-соня");

            while (!Thread.currentThread().isInterrupted()){
                sleepAll();
            }
        });
    }

    public void addTamagochiVlad(TamagochiVlad vlad) {
        if(vlad == null)
            throw new NullPointerException("The Tamagochi can't be null");
        vlads.add(vlad);
    }

    public void removeTamagochiVlad(TamagochiVlad vlad) {
        if(vlad == null)
            throw new NullPointerException("The Tamagochi can't be null");
        vlads.remove(vlad);
    }

    private void feedAll() {
        vlads.forEach(TamagochiVlad::feed);
    }

    private void playAll() {
        vlads.forEach(TamagochiVlad::play);
    }

    private void cleanAll() {
        vlads.forEach(TamagochiVlad::clean);
    }

    private void sleepAll() {
        vlads.forEach(TamagochiVlad::sleep);
    }
}