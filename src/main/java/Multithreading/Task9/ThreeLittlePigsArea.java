package Multithreading.Task9;

import java.util.List;

public class ThreeLittlePigsArea {
    public static void main(String[] args) {
        NifNifThread nifnif = new NifNifThread("Nif-Nif", Material.WOOD);
        NafNafThread nafnaf = new NafNafThread("Naf-Naf", Material.STONE);
        NufNufThread nufnuf = new NufNufThread("Nuf-Nuf", Material.BRICK);

        List<Thread> pigs = List.of(nifnif, nafnaf, nufnuf);
        pigs.forEach(Thread::start);

        for (Thread pig : pigs) {
            try {
                pig.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Главный поток был прерван!");
            }
        }

        System.out.println("Хрюшки успешно достроили свои домики!");
    }
}