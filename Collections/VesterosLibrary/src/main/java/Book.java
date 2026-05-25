import java.time.LocalDateTime;
import java.util.Objects;

public class Book {
    private final String name;
    private final String author;
    private final int year;

    public Book(String name, String author, int year){
        if(name == null)
            throw new NullPointerException("The name should be not null");
        if(!validString(name) || !validString(author))
            throw new IllegalArgumentException("Illegal data for name");
        if(!validYear(year))
            throw new IllegalArgumentException("Illegal data for year");
        this.name = name;
        this.author = author;
        this.year = year;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, author, year);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null || this.getClass() != obj.getClass())
            return false;

        Book book = (Book) obj;

        return Objects.equals(this.name, book.name)
                && Objects.equals(this.author, book.author) && this.year == book.year;
    }

    @Override
    public String toString() {
        return "Book{" +
                "author='" + author + '\'' +
                ", name='" + name + '\'' +
                ", year=" + year +
                '}';
    }

    private boolean validString(String string){
        if(string.isBlank())
            throw new IllegalArgumentException("Invalid string");

        return true;
    }

    private boolean validYear(int year){
        if(year > LocalDateTime.now().getYear() || year < 0)
            throw new IllegalArgumentException("Illegal data for year");

        return true;
    }
}