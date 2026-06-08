package Multithreading.Task1;

import java.util.List;

public class SenderRunnable implements Runnable {
    private final List<Letter> letters;

    public SenderRunnable(List<Letter> letters) {
        //check null & empty for list type
        this.letters = letters;
    }

    @Override
    public void run() {
        for (var letter : letters){
            System.out.println(Thread.currentThread().getName() + " Отправил письмо " + letter.getName());
        }
    }
}