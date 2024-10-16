import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class XmlParser {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("random_structure_19.xml"));
            StringBuilder xmlContent = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                xmlContent.append(line.trim());
            }
            reader.close();

            Library library = parseLibrary(xmlContent.toString());
            System.out.println(library);

            // Генерация XML из объекта
            String generatedXml = library.toXml();
            System.out.println(generatedXml);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Library parseLibrary(String xml) {
        Library library = new Library();
        String[] bookBlocks = xml.split("</book>");
        for (String bookBlock : bookBlocks) {
            if (bookBlock.contains("<book")) {
                Book book = parseBook(bookBlock);
                library.addBook(book);
            }
        }
        return library;
    }

    private static Book parseBook(String xml) {
        Book book = new Book();

        book.setId(Integer.parseInt(getTagValue(xml, "book id", "id=\"", "\"")));
        book.setTitle(getTagValue(xml, "title"));
        book.setAuthor(getTagValue(xml, "author"));
        book.setYear(Integer.parseInt(getTagValue(xml, "year")));
        book.setGenre(getTagValue(xml, "genre"));

        String priceValue = getTagContent(xml, "price");  // Используем новый метод getTagContent
        if (!priceValue.isEmpty()) {
            Price price = new Price();
            price.setAmount(Double.parseDouble(priceValue));  // Преобразуем строку в число
            price.setCurrency(getTagAttribute(xml, "price", "currency"));  // Извлекаем валюту
            book.setPrice(price);
            System.out.println("Price: " + priceValue);  // Отладочный вывод
        } else {
            book.setPrice(null);
            System.out.println("Price: (none)");  // Отладочный вывод для случая отсутствия цены
        }

        book.setIsbn(getTagValue(xml, "isbn"));

        // Проверяем наличие издателя
        if (xml.contains("<publisher>")) {
            Publisher publisher = new Publisher();
            publisher.setName(getTagValue(xml, "name"));
            Address address = new Address();
            address.setCity(getTagValue(xml, "city"));
            address.setCountry(getTagValue(xml, "country"));
            publisher.setAddress(address);
            book.setPublisher(publisher);
        } else {
            book.setPublisher(null);  // Если издателя нет, ставим null
        }

        // Парсинг языка (если отсутствует тег, устанавливаем null)
        String language = getTagValue(xml, "language");
        book.setLanguage(language.isEmpty() ? null : language);

        book.setTranslator(getTagValue(xml, "translator"));

        // Парсим награды (если они есть)
        if (xml.contains("<awards>")) {
            String awardsBlock = getTagBlock(xml, "awards");
            String[] awardTags = awardsBlock.split("</award>");
            for (String awardTag : awardTags) {
                if (awardTag.contains("<award>")) {
                    Award award = new Award();
                    String awardName = getTagValue(awardTag, "award");
                    if (!awardName.isEmpty()) {
                        award.setName(awardName);
                        book.addAward(award);
                    }
                }
            }
        }

        // Парсим отзывы (если они есть)
        if (xml.contains("<reviews>")) {
            String reviewsBlock = getTagBlock(xml, "reviews");
            String[] reviewTags = reviewsBlock.split("</review>");
            for (String reviewTag : reviewTags) {
                if (reviewTag.contains("<review>")) {
                    Review review = new Review();
                    review.setUser(getTagValue(reviewTag, "user"));
                    review.setRating(Integer.parseInt(getTagValue(reviewTag, "rating")));
                    review.setComment(getTagValue(reviewTag, "comment"));
                    book.addReview(review);
                }
            }
        }

        return book;
    }


    private static String getTagValue(String xml, String tag) {
        return getTagValue(xml, tag, "<" + tag + ">", "</" + tag + ">");
    }

    private static String getTagValue(String xml, String tag, String openTag, String closeTag) {
        int start = xml.indexOf(openTag);
        if (start == -1) {
            return "";  // Если тег не найден, возвращаем пустую строку
        }
        int end = xml.indexOf(closeTag, start + openTag.length());
        if (end == -1) {
            return "";  // Если закрывающий тег не найден, возвращаем пустую строку
        }
        return xml.substring(start + openTag.length(), end).trim();
    }


    private static String getTagAttribute(String xml, String tag, String attribute) {
        String tagOpen = "<" + tag;
        int tagPos = xml.indexOf(tagOpen);
        if (tagPos == -1) {
            return "";  // Если открывающий тег не найден, возвращаем пустую строку
        }
        int attrPos = xml.indexOf(attribute + "=\"", tagPos);
        if (attrPos == -1) {
            return "";  // Если атрибут не найден, возвращаем пустую строку
        }
        int attrEnd = xml.indexOf("\"", attrPos + attribute.length() + 2);
        if (attrEnd == -1) {
            return "";  // Если конец атрибута не найден, возвращаем пустую строку
        }
        return xml.substring(attrPos + attribute.length() + 2, attrEnd);
    }


    private static String getTagBlock(String xml, String tag) {
        String openTag = "<" + tag + ">";
        String closeTag = "</" + tag + ">";
        int start = xml.indexOf(openTag);
        int end = xml.indexOf(closeTag, start);
        if (start == -1 || end == -1) return "";
        return xml.substring(start + openTag.length(), end).trim();
    }

    // Метод используется тогда когда getTagBlock не справляется
    private static String getTagContent(String xml, String tag) {
        String openTag = "<" + tag;
        String closeTag = "</" + tag + ">";
        int start = xml.indexOf(openTag);
        if (start == -1) {
            return "";  // Тег не найден
        }
        int contentStart = xml.indexOf(">", start) + 1;  // Позиция начала содержимого (после >)
        int contentEnd = xml.indexOf(closeTag, contentStart);
        if (contentEnd == -1) {
            return "";  // Закрывающий тег не найден
        }
        return xml.substring(contentStart, contentEnd).trim();
    }

}
