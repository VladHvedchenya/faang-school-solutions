package Multithreading.Task13;

public class GoogleCompany {
    public static void main(String[] args){
        GooglePhotoAutoLoader loader = new GooglePhotoAutoLoader();
        Thread loadThread = new Thread(loader::startAutoUpload);
        Thread createThread = new Thread(loader::addPhoto);
        loadThread.start();
        createThread.start();
    }
}