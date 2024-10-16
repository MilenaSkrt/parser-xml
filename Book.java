import java.util.ArrayList;
import java.util.List;

public class Book {
    private int id;
    private String title;
    private String author;
    private int year;
    private String genre;
    private Price price;
    private String isbn;
    private Publisher publisher;
    private String language;
    private String translator;
    private List<Award> awards;
    private List<Review> reviews;

    public Book() {
        awards = new ArrayList<>();
        reviews = new ArrayList<>();
    }

    // Геттеры и сеттеры для всех атрибутов
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public Price getPrice() { return price; }
    public void setPrice(Price price) { this.price = price; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public Publisher getPublisher() { return publisher; }
    public void setPublisher(Publisher publisher) { this.publisher = publisher; }

    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }

    public String getTranslator() { return translator; }
    public void setTranslator(String translator) { this.translator = translator; }

    public List<Award> getAwards() { return awards; }
    public void addAward(Award award) { awards.add(award); }

    public List<Review> getReviews() { return reviews; }
    public void addReview(Review review) { reviews.add(review); }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + (title != null ? title : "Значение пусто") + '\'' +
                ", author='" + (author != null ? author : "Значение пусто") + '\'' +
                ", year=" + year +
                ", genre='" + (genre != null ? genre : "Значение пусто") + '\'' +
                ", price=" + (price != null ? price : "Значение пусто") +
                ", isbn='" + (isbn != null ? isbn : "Значение пусто") + '\'' +
                ", publisher=" + (publisher != null ? publisher : "Значение пусто") +
                ", language='" + (language != null ? language : "Значение пусто") + '\'' +
                ", translator='" + (translator != null ? translator : "Значение пусто") + '\'' +
                ", awards=" + (!awards.isEmpty() ? awards : "Список пуст") +
                ", reviews=" + (!reviews.isEmpty() ? reviews : "Список пуст") +
                '}';
    }

    public String toXml() {
        StringBuilder xml = new StringBuilder();
        xml.append("<book id=\"").append(id).append("\">\n");
        xml.append("<title>").append(title != null ? title : "").append("</title>\n");
        xml.append("<author>").append(author != null ? author : "").append("</author>\n");
        xml.append("<year>").append(year).append("</year>\n");
        xml.append("<genre>").append(genre != null ? genre : "").append("</genre>\n");

        if (price != null) {
            xml.append("<price currency=\"").append(price.getCurrency() != null ? price.getCurrency() : "").append("\">")
                    .append(price.getAmount()).append("</price>\n");
        }

        xml.append("<isbn>").append(isbn != null ? isbn : "").append("</isbn>\n");

        if (publisher != null) {
            xml.append(publisher.toXml()).append("\n");
        }

        xml.append("<language>").append(language != null ? language : "").append("</language>\n");
        xml.append("<translator>").append(translator != null ? translator : "").append("</translator>\n");

        if (!awards.isEmpty()) {
            xml.append("<awards>\n");
            for (Award award : awards) {
                xml.append(award.toXml()).append("\n");
            }
            xml.append("</awards>\n");
        }

        if (!reviews.isEmpty()) {
            xml.append("<reviews>\n");
            for (Review review : reviews) {
                xml.append(review.toXml()).append("\n");
            }
            xml.append("</reviews>\n");
        }

        xml.append("</book>");
        return xml.toString();
    }

}
