package Collections.VesterosLibrary.src.main.java;

import java.util.HashMap;
import java.util.Map;

public class LibrarySystem {
    private final Map<Book, String> map = new HashMap<>();

    public boolean addBook(Book book, String location){
        if (book == null)
            return false;

        if (map.containsKey(book)){
            System.out.println("The book is already on the bookshelf");
            return false;
        }

        map.put(book, location);
        return true;
    }

    public boolean removeBook(Book book){
        return map.remove(book) != null;
    }

    public String findBook(Book book){
        return map.getOrDefault(book, "is not found");
    }

    public void printAllBook(){
        for (Map.Entry<Book, String> entry : map.entrySet()){
            System.out.println(entry.getKey().toString() + " is on the " + entry.getValue() + " shelf");
        }
    }
}