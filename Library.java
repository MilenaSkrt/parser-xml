import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    public List<Book> getBooks() {
        return books;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Book book : books) {
            result.append(book.toString()).append("\n");
        }
        return result.toString();
    }

    //Доп задание
    public String toXml() {
        StringBuilder xml = new StringBuilder();
        xml.append("<library>\n");
        for (Book book : books) {
            xml.append(book.toXml()).append("\n");
        }
        xml.append("</library>");
        return xml.toString();
    }

}
