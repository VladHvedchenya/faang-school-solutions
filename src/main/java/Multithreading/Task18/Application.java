package Multithreading.Task18;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Application {
    public static void main(String[] args){
        List<TamagochiVlad> vlads = new ArrayList<>();
        vlads.add(new TamagochiVlad("LABUBA"));
        VladController controller = new VladController(vlads);
        ExecutorService executor = Executors.newFixedThreadPool(4);
        controller.start(executor);
    }
}