package Multithreading.Task21;

public class Chat {
    private final User first;
    private final User second;

    public Chat(User first, User second){
        this.first = first;
        this.second = second;
    }

    public User getFirst() { return first; }
    public User getSecond() { return second; }

    public void start(){
        first.setLookingForChat(false);
        second.setLookingForChat(false);
    }

    public void end(){
        first.setLookingForChat(true);
        second.setLookingForChat(true);
    }
}
