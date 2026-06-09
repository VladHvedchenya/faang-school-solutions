package Multithreading.Task13;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class GooglePhotoAutoLoader {
    private final Object lock = new Object();
    private Set<String> filePaths = new HashSet<>();

    public void startAutoUpload(){
        while (true) {
            synchronized (lock) {
                while (filePaths.size() < 3) {
                    try {
                        System.out.println("[Загрузчик] В папке меньше 3 элементов. Ухожу в режим ожидания...");
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        System.out.println("Поток загрузки был прерван");
                        return;
                    }
                }

                uploadPhotos();
            }
        }
    }

    public void addPhoto(){
        while (true) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Поток добавления был прерван");
                return;
            }

            String randomPath = "C:/photos/" + UUID.randomUUID().toString().substring(0, 8) + ".jpg";

            synchronized (lock) {
                filePaths.add(randomPath);
                System.out.println("[Добавление] Новое фото на сервере локально: " + randomPath);

                if(filePaths.size() >= 3)
                    lock.notify();
            }
        }
    }

    private void uploadPhotos(){
        Set<String> uploadedPhotos = new HashSet<>();

        for (var path : filePaths){
            try{
                Thread.sleep(1000);
                System.out.println("[Загрузчик] фотографию с путем: " + path);
                uploadedPhotos.add(path);
            }
            catch (InterruptedException e){
                Thread.currentThread().interrupt();
                System.out.println("Поток прерван");
                return;
            }
        }

        filePaths.removeAll(uploadedPhotos);
    }
}